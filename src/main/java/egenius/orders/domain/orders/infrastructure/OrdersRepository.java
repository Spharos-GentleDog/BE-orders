package egenius.orders.domain.orders.infrastructure;

import egenius.orders.domain.orders.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long>{

}
