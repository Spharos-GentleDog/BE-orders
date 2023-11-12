package egenius.orders.domain.orders.infrastructure;

import egenius.orders.domain.orders.entity.OrdersDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersDetailRepository extends JpaRepository<OrdersDetail, Long> {
}
