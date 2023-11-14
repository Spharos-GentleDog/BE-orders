package egenius.orders.domain.orders.infrastructure;

import egenius.orders.domain.orders.entity.VendorOrdersList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorOrdersListRepository extends JpaRepository<VendorOrdersList, Long> {
}
