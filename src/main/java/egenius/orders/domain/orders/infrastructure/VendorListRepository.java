package egenius.orders.domain.orders.infrastructure;

import egenius.orders.domain.orders.entity.VendorList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorListRepository extends JpaRepository<VendorList, Long> {
}
