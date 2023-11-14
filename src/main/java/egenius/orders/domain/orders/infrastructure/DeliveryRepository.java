package egenius.orders.domain.orders.infrastructure;

import egenius.orders.domain.orders.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
