package egenius.orders.domain.orders.infrastructure;

import egenius.orders.domain.orders.entity.RefundList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefundListRepository extends JpaRepository<RefundList, Long> {
}
