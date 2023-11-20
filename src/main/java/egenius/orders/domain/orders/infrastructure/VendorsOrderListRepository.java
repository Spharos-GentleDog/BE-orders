package egenius.orders.domain.orders.infrastructure;

import egenius.orders.domain.orders.entity.VendorsOrderList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendorsOrderListRepository extends JpaRepository<VendorsOrderList, Long> {
    List<VendorsOrderList> findAllByVendorEmail(String vendorEmail);
    Boolean existsByOrderNumber(String orderNumber);
}
