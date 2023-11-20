package egenius.orders.domain.orders.application.user;

import egenius.orders.domain.orders.dto.in.OrderDetailInRequestDto;
import egenius.orders.domain.orders.dto.in.OrderRegisterRequestDto;
import egenius.orders.domain.orders.dto.in.VendorsOrderListInRequestDto;
import egenius.orders.domain.orders.dto.out.OrdersSuccessResponseDto;
import egenius.orders.domain.orders.entity.*;
import egenius.orders.domain.orders.entity.enums.DeliveryStatus;
import egenius.orders.domain.orders.entity.enums.OrderDetailStatus;
import egenius.orders.domain.orders.entity.enums.VendorsOrderListStatus;
import egenius.orders.domain.orders.infrastructure.DeliveryRepository;
import egenius.orders.domain.orders.infrastructure.OrderDetailRepository;
import egenius.orders.domain.orders.infrastructure.VendorsOrderListRepository;
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
public class UserOrdersServiceImple implements UserOrdersService {

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

        // 배송지 테이블 생성
        Delivery delivery = modelMapper.map(orderRegisterRequestDto.getDeliveryOrdersInRequestDto(), Delivery.class);
        delivery = delivery.toBuilder()
                .deliveryStatus(DeliveryStatus.READY)
                .build();

        deliveryRepository.save(delivery);

        for(VendorsOrderListInRequestDto vendorsOrderListInRequestDto:
                orderRegisterRequestDto.getVendorsOrderListInRequestDto()) {

            List<OrderDetailInRequestDto> orderDetailInRequestDtoList = vendorsOrderListInRequestDto.getOrderDetailList();
            // 주문 상세 테이블 생성
            List<OrderDetail> orderDetails = new ArrayList<>();
            orderDetailInRequestDtoList.forEach(item -> {
                log.info("item : {}", item);
                OrderDetail orderDetail = modelMapper.map(item, OrderDetail.class);
                log.info("orderDetail : {}", orderDetail);
                orderDetail = orderDetail.toBuilder()
                        .productDeleteStatus(1)
                        .build();
                orderDetailRepository.save(orderDetail);
                orderDetails.add(orderDetail);

            });
            log.info("orderDetails : {}", orderDetails);
            // 벤더주문리스트 테이블 생성 후, 주문 상세 테이블을 업데이트하고 저장

            VendorsOrderList vendorsOrdersList = modelMapper.map(vendorsOrderListInRequestDto, VendorsOrderList.class);
            log.info("vendorsOrdersList : {}", vendorsOrdersList);
            vendorsOrdersList = vendorsOrdersList.toBuilder()
                    .userName(userEmail)
                    .orderNumber(orderNumber)
                    .orderType(1)
                    .vendorsOrderListStatus(VendorsOrderListStatus.READY)
                    .delivery(delivery)
                    .orderDetailList(orderDetails)
                    .build();
            log.info("vendorsOrdersList : {}", vendorsOrdersList);
            vendorsOrderListRepository.save(vendorsOrdersList);
        }

        return OrdersSuccessResponseDto.builder()
                .orderNumber(orderNumber)
                .build();
    }

//    // 주문 요약 조회
//    @Override
//    public Slice<OrdersSummaryOutResponseDto> getOrdersSummary(Long ordersId, String userEmail, Pageable pageable) {
//        return ordersDetailRepository.findByFilter(ordersId, userEmail, pageable);
//    }
}