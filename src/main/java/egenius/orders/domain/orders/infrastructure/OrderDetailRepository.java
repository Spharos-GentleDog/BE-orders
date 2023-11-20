package egenius.orders.domain.orders.infrastructure;

import egenius.orders.domain.orders.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}
