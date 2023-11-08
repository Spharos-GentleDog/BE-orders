package egenius.orders.domain.payment.infrastructure;

import egenius.orders.domain.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    /**
     * 1. 결제 키로 조회
     * 2. 승인 날짜별 조회
     */

    // 1. 결제 키로 조회
    Optional<Payment> findByPaymentKey(String paymentKey);

    // 2. 승인 날짜별 조회
    List<Payment> findByApprovedAtBetween(LocalDateTime stt, LocalDateTime end);
}
