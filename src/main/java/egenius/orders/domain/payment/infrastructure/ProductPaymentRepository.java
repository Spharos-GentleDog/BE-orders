package egenius.orders.domain.payment.infrastructure;

import egenius.orders.domain.payment.entity.ProductPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPaymentRepository extends JpaRepository<ProductPayment, Long> {
}
