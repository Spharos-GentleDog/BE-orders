package egenius.orders.domain.orders.application;

import egenius.orders.domain.orders.dto.RegisterInRequestDto;
import egenius.orders.domain.orders.infrastructure.DeliveryOrdersRepository;
import egenius.orders.domain.orders.infrastructure.OrdersDetailRepository;
import egenius.orders.domain.orders.infrastructure.OrdersRepository;
import egenius.orders.domain.orders.infrastructure.VendorListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrdersServiceImple implements OrdersService{

    private final OrdersRepository ordersRepository;
    private final OrdersDetailRepository ordersDetailRepository;
    private final VendorListRepository vendorListRepository;
    private final DeliveryOrdersRepository deliveryOrdersRepository;

    /**
     * 1. 주문 등록
     * 2. 주문 조회
     * 3. 주문 취소
     * 4. 주문 수정
     * 5. 반품 요청
     * 6. 교환 요청
     */

    /**
     *
     * @param registerInRequestDto
     */

    @Override
    public void registerOrders(RegisterInRequestDto registerInRequestDto) {


    }
}
