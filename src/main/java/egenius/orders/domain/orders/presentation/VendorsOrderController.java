//package egenius.orders.domain.orders.presentation;
//
//import egenius.orders.domain.orders.dto.in.DeliveryNumberRegisterRequestDto;
//import egenius.orders.domain.orders.webdto.out.OrdersVendorFindOutWebDto;
//import egenius.orders.global.common.response.BaseResponse;
//import io.swagger.v3.oas.annotations.Operation;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.modelmapper.ModelMapper;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1/orders/vendor")
//@RequiredArgsConstructor
//@Slf4j
//public class VendorsOrderController {
//
//    private final ModelMapper modelMapper;
//    private final VendorOrdersService vendorOrdersService;
//    // 1. 판매자 주문 조회
//    @Operation(summary = "판매자 주문 조회", description = "판매자 주문 조회", tags = { "Orders Vendor" })
//    @GetMapping("")
//    public BaseResponse<List<OrdersVendorFindOutWebDto>> getVendorOrders(@RequestHeader("vendorEmail") String vendorEmail){
//
//
//        List<VendorOrdersOutResponseDto> vendorOrdersOutResponseDtoList =
//                vendorOrdersService.getVendorOrders(vendorEmail);
//        List<OrdersVendorFindOutWebDto> OrdersVendor = new ArrayList<>();
//        vendorOrdersOutResponseDtoList.forEach(item -> {
//            OrdersVendorFindOutWebDto ordersVendorFindOutWebDto = modelMapper.map(item, OrdersVendorFindOutWebDto.class);
//            OrdersVendor.add(ordersVendorFindOutWebDto);
//        });
//
//        return new BaseResponse<>(OrdersVendor);
//    }
//
//    // 2. 판매자 주문 상세 조회
//
//    // 3. 판매자 운송장 번호 등록
//    @Operation(summary = "판매자 운송장 번호 등록", description = "판매자 운송장 번호 등록", tags = { "Orders Vendor" })
//    @PostMapping("/deliveryNumber")
//    public BaseResponse<Void> registerDeliveryNumber(@RequestHeader("vendorEmail") String vendorEmail,
//                                                     @RequestBody DeliveryNumberRegisterRequestDto
//                                                             deliveryNumberRegisterRequestDto) {
//        vendorOrdersService.registerDeliveryNumber(vendorEmail, deliveryNumberRegisterRequestDto.getDeliveryNumber());
//        return new BaseResponse<>();
//    }
//
//
//}
