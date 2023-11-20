//package egenius.orders.domain.orders.application.vendor;
//
//import egenius.orders.domain.orders.dto.out.VendorOrdersOutResponseDto;
//import egenius.orders.domain.orders.entity.Delivery;
//import egenius.orders.domain.orders.entity.VendorsOrderList;
//import egenius.orders.domain.orders.infrastructure.DeliveryRepository;
//import egenius.orders.domain.orders.infrastructure.OrdersDetailRepository;
//import egenius.orders.domain.orders.infrastructure.VendorsOrderListRepository;
//import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Comparator;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class VendorOrdersServiceImple implements VendorOrdersService{
//
//    private final ModelMapper modelMapper;
//    private final OrdersDetailRepository ordersDetailRepository;
//    private final VendorsOrderListRepository vendorsOrderListRepository;
//    private final DeliveryRepository deliveryRepository;
//
//
//    @Override
//    public List<VendorOrdersOutResponseDto> getVendorOrders(String vendorEmail) {
//        List<VendorsOrderList> vendorsOrderList = vendorsOrderListRepository.findAllByVendorEmail(vendorEmail);
//
//        return vendorsOrderList.stream()
//                .map(item -> {
//                    Orders orders = ordersRepository.findById(item.getOrders().getId()).get();
//                    Delivery delivery = deliveryRepository.findById(item.getId()).get();
//
//                    List<VendorOrdersOutResponseDto.OrdersDetailOutResponseDto> ordersDetailList =
//                            item.getOrdersDetailList().stream()
//                            .filter(ordersDetail -> ordersDetail.getProductDeleteStatus() == 1)
//                            .map(ordersDetail -> VendorOrdersOutResponseDto.OrdersDetailOutResponseDto.builder()
//                                    .productId(ordersDetail.getProductId())
//                                    .productName(ordersDetail.getProductName())
//                                    .productImageUrl(ordersDetail.getProductImageUrl())
//                                    .productPrice(ordersDetail.getProductPrice())
//                                    .productSize(ordersDetail.getProductSize())
//                                    .productColor(ordersDetail.getProductColor())
//                                    .productStock(ordersDetail.getProductStock())
//                                    .productOrdersStatus(ordersDetail.getProductOrdersStatus())
//                                    .productDiscountRate(ordersDetail.getProductDiscountRate())
//                                    .build())
//                            .toList();
//
//                    return VendorOrdersOutResponseDto.builder()
//                            .ordersId(orders.getId())
//                            .ordersNumber(orders.getOrdersNumber())
//                            .userImageUrl(orders.getUserImageUrl())
//                            .userName(orders.getUserName())
//                            .userEmail(orders.getUserEmail())
//                            .ordersRequestMessage(orders.getOrdersRequestMessage())
//                            .orderDate(orders.getCreatedAt())
//                            .deliveryDate(delivery.getUpdatedAt())
//                            .ordersDetailList(ordersDetailList)
//                            .build();
//                })
//                .sorted(Comparator.comparing(VendorOrdersOutResponseDto::getOrdersId).reversed())
//                .toList();
//    }
//
//    /**
//     * @param vendorEmail
//     * @param deliveryNumber
//     */
//    @Override
//    public void registerDeliveryNumber(String vendorEmail, String deliveryNumber) {
//
//    }
//}
