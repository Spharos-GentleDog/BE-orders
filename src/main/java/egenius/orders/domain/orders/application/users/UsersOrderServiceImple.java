package egenius.orders.domain.orders.application.users;

import egenius.orders.domain.orders.dto.in.users.OrderDetailInRequestDto;
import egenius.orders.domain.orders.dto.in.users.OrderRegisterRequestDto;
import egenius.orders.domain.orders.dto.in.users.VendorsOrderListInRequestDto;
import egenius.orders.domain.orders.dto.out.users.OrdersSuccessResponseDto;
import egenius.orders.domain.orders.dto.out.users.VendorsOrderSearchOutResponseDto;
import egenius.orders.domain.orders.dto.out.users.VendorsOrderSummaryOutResponseDto;
import egenius.orders.domain.orders.entity.*;
import egenius.orders.domain.orders.entity.enums.DeliveryStatus;
import egenius.orders.domain.orders.entity.enums.VendorsOrderListStatus;
import egenius.orders.domain.orders.infrastructure.DeliveryRepository;
import egenius.orders.domain.orders.infrastructure.OrderDetailRepository;
import egenius.orders.domain.orders.infrastructure.VendorsOrderListRepository;
import egenius.orders.global.common.exception.BaseException;
import egenius.orders.global.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UsersOrderServiceImple implements UsersOrderService {

    private final ModelMapper modelMapper;
    private final OrderDetailRepository orderDetailRepository;
    private final VendorsOrderListRepository vendorsOrderListRepository;
    private final DeliveryRepository deliveryRepository;


    public String createdCode() {
        int leftLimit = 65; // alphabet 'A'
        int rightLimit = 90; // alphabet 'Z'
        int targetStringLength = 7;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i >=65) && (i <= 90))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Override
    public OrdersSuccessResponseDto registerOrders(String userEmail, OrderRegisterRequestDto orderRegisterRequestDto) {

        /**
         * 주문 들어올 시 생성(연월일(YYMMDD) + 영문자 7자리 예 : 201011A1B2C3) 중복체크
         */
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = currentDate.format(formatter);
        // 랜덤 문자열 생성
        String randomString = createdCode();
        // 주문 번호 생성
        String orderNumber = formattedDate + randomString;
        // 주문번호 유효성 검사
        while (vendorsOrderListRepository.existsByOrderNumber(orderNumber)) {
            randomString = createdCode();
            orderNumber = formattedDate + randomString;
        }

        // 최신 그룹 ID 조회
        VendorsOrderList vendorsOrderList = vendorsOrderListRepository.findMaxGroupId(userEmail);
        Long groupId;

        // VendorsOrderList에 주문 내역이 있는지 확인
        if (vendorsOrderList == null) {
            groupId = 1L;
        } else if (vendorsOrderList.getCreatedAt().toLocalDate().equals(currentDate)) {
            groupId = vendorsOrderList.getGroupId();

        } else {
            groupId = vendorsOrderList.getGroupId()+1;
        }

        // 배송지 테이블 생성
        Delivery delivery = modelMapper.map(orderRegisterRequestDto.getDeliveryOrdersInRequestDto(), Delivery.class);
        delivery = delivery.toBuilder()
                .deliveryStatus(DeliveryStatus.READY)
                .build();

        deliveryRepository.save(delivery);

        for(VendorsOrderListInRequestDto vendorsOrderListInRequestDto:
                orderRegisterRequestDto.getVendorsOrderListInRequestDto()) {
            log.info("vendorsOrderListInRequestDto getusername: {}", vendorsOrderListInRequestDto.getUserName());
            List<OrderDetailInRequestDto> orderDetailInRequestDtoList = vendorsOrderListInRequestDto.getOrderDetailList();

            // 주문 상세 테이블 생성
            List<OrderDetail> orderDetails = new ArrayList<>();
            orderDetailInRequestDtoList.forEach(item -> {
                log.info("item : {}", item);
                OrderDetail orderDetail = modelMapper.map(item, OrderDetail.class);
                log.info("orderDetail : {}", orderDetail);
                orderDetail = orderDetail.toBuilder()
                        .build();
                orderDetailRepository.save(orderDetail);
                orderDetails.add(orderDetail);

            });
            log.info("orderDetails : {}", orderDetails);
            // 벤더주문리스트 테이블 생성 후, 주문 상세 테이블을 업데이트하고 저장

            VendorsOrderList vendorsOrdersList = modelMapper.map(vendorsOrderListInRequestDto, VendorsOrderList.class);

            log.info("vendorsOrdersList : {}", vendorsOrdersList.getUserName());
            vendorsOrdersList = vendorsOrdersList.toBuilder()
                    .groupId(groupId)
                    .userEmail(userEmail)
                    .orderNumber(orderNumber)
                    .vendorsOrderListStatus(VendorsOrderListStatus.READY)
                    .delivery(delivery)
                    .orderDetailList(orderDetails)
                    .orderDeleteStatus(1)
                    .build();

            log.info("vendorsOrdersList : {}", vendorsOrdersList.getUserName());
            vendorsOrderListRepository.save(vendorsOrdersList);

            // 주문 상세 테이블에 벤더주문리스트 테이블을 업데이트
            for (OrderDetail item : orderDetails) {
                item.updateVendorsOrderList(vendorsOrdersList);
                orderDetailRepository.save(item);
            }
        }

        return OrdersSuccessResponseDto.builder()
                .orderNumber(orderNumber)
                .build();
    }

    // 주문 요약 조회
    @Override
    public VendorsOrderSearchOutResponseDto getOrdersSummary(String userEmail, Long groupId) {

        if (!vendorsOrderListRepository.existsByUserEmail(userEmail)) {
            throw new BaseException(BaseResponseStatus.NOT_EXIST_ORDER);
        }

        List<VendorsOrderSummaryOutResponseDto> vendorsOrderSummaryOutResponseDto =
                vendorsOrderListRepository.findByFilter(userEmail, groupId);

        // vendorsOrderSummaryOutResponseDto 에서 GroupId는 모두 같기때문에 첫 값에서 가져온다.
        Long lastElement = vendorsOrderSummaryOutResponseDto.get(0).getGroupId();

        log.info("lastElement : {}", lastElement);
        log.info("groupId : {}", groupId);
        VendorsOrderList vendorsOrderList = vendorsOrderListRepository.
                findByNextGroupId(userEmail, lastElement);

        // vendorsOrderList가 null이라면 nextGroupId는 null로 전달
        Long nextGroupId = vendorsOrderList == null ? null : vendorsOrderList.getGroupId();

        log.info("nextGroupId : {}", nextGroupId);

        // vendorsOrderSummaryOutResponseDto의 마지막 요소의 cursorId를 nextCursorId로 전달
        return VendorsOrderSearchOutResponseDto.builder()
                .vendorsOrderSummaryOutResponseDtoList(vendorsOrderSummaryOutResponseDto)
                .nextGroupId(nextGroupId)
                .build();
    }

    /**
     * @param userEmail
     * @param orderNumber
     */
    @Override
    public void deleteOrder(String userEmail, String orderNumber) {
        List<VendorsOrderList> vendorsOrderList = vendorsOrderListRepository.findByUserEmailAndOrderNumber(userEmail, orderNumber);

        vendorsOrderList.forEach(VendorsOrderList::updateOrderDeleteStatus);
    }
}