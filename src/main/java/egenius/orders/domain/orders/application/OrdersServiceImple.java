package egenius.orders.domain.orders.application;

import egenius.orders.domain.orders.dto.OrdersDetailInRequestDto;
import egenius.orders.domain.orders.dto.OrdersRegisterRequestDto;
import egenius.orders.domain.orders.dto.VendorOrdersListInRequestDto;
import egenius.orders.domain.orders.entity.Delivery;
import egenius.orders.domain.orders.entity.Orders;
import egenius.orders.domain.orders.entity.OrdersDetail;
import egenius.orders.domain.orders.entity.VendorOrdersList;
import egenius.orders.domain.orders.infrastructure.DeliveryRepository;
import egenius.orders.domain.orders.infrastructure.OrdersDetailRepository;
import egenius.orders.domain.orders.infrastructure.OrdersRepository;
import egenius.orders.domain.orders.infrastructure.VendorOrdersListRepository;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.atn.SemanticContext;
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
@Transactional
public class OrdersServiceImple implements OrdersService{

    private final ModelMapper modelMapper;
    private final OrdersRepository ordersRepository;
    private final OrdersDetailRepository ordersDetailRepository;
    private final VendorOrdersListRepository vendorOrdersListRepository;
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
    public void registerOrders(OrdersRegisterRequestDto ordersRegisterRequestDto) {
        // 주문 테이블 생성
        Orders orders = modelMapper.map(ordersRegisterRequestDto.getOrdersInRequestDto(), Orders.class);

        /**
         * 주문 들어올 시 생성(연월일(YYMMDD) + 영문자 7자리 예 : 201011A1B2C3) 중복체크
         */
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = currentDate.format(formatter);
        // 랜덤 문자열 생성
        String randomString = createdCode();
        // 주문 번호 생성
        String ordersNumber = formattedDate + randomString;
        // 주문번호 유효성 검사
        while (ordersRepository.existsByOrdersNumber(ordersNumber)) {
            randomString = createdCode();
            ordersNumber = formattedDate + randomString;
        }

        // 주문 번호 넣고 저장
        orders = orders.toBuilder()
                .ordersNumber(ordersNumber)
                .build();
        ordersRepository.save(orders);

        // 주문 배송 테이블 생성
        Delivery delivery = modelMapper.map(ordersRegisterRequestDto.getDeliveryOrdersInRequestDto(),
                Delivery.class);
        deliveryRepository.save(delivery);

        // 주문 상세 테이블과 벤더주문리스트 테이블 생성
        for (VendorOrdersListInRequestDto vendorOrdersListDto :
                ordersRegisterRequestDto.getVendorOrdersListInRequestDto()) {

            // vendorOrdersListDto에 있는 OrdersDetailInRequestDto 리스트를 가져옴
            List<OrdersDetailInRequestDto> ordersDetailInRequestDtoList = vendorOrdersListDto.getOrdersDetailInRequestDtoList();

            // ordersDetailInRequestDtoList를 하나씩 꺼내서 ordersDetail 테이블에 저장
            List<OrdersDetail> ordersDetaillist = new ArrayList<>();
            ordersDetailInRequestDtoList.forEach(item -> {
                OrdersDetail ordersDetail1 = modelMapper.map(item, OrdersDetail.class);
                ordersDetailRepository.save(ordersDetail1);
                ordersDetaillist.add(ordersDetail1);

            });

            // 벤더주문리스트 테이블 생성 후, 주문 상세 테이블을 업데이트하고 저장
            VendorOrdersList vendorOrdersList = modelMapper.map(vendorOrdersListDto, VendorOrdersList.class);
            vendorOrdersList = vendorOrdersList.toBuilder()
                    .orders(orders)
                    .delivery(delivery)
                    .ordersDetailList(ordersDetaillist)
                    .ordersNumber(ordersNumber)
                    .build();

            vendorOrdersListRepository.save(vendorOrdersList);
            }
    }
}