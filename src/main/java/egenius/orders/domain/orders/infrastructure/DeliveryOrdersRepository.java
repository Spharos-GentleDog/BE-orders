package egenius.orders.domain.orders.infrastructure;

import egenius.orders.domain.orders.entity.DeliveryOrders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryOrdersRepository extends JpaRepository<DeliveryOrders, Long> {
}
