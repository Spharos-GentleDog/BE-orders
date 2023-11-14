package egenius.orders.domain.orders.infrastructure;

import egenius.orders.domain.orders.entity.Refund;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefundRepository extends JpaRepository<Refund, Long> {
}
