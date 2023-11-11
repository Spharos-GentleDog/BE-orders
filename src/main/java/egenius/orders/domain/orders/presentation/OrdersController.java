package egenius.orders.domain.orders.presentation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrdersController {

    /**
     * 1. 주문 생성
     * 2. 주문 조회
     */

    // 1. 주문 생성

    @PostMapping("")
    public void createOrder() {

    }

    @GetMapping("")
    public void getOrder() {

    }

    // 2. 주문 조회
}
