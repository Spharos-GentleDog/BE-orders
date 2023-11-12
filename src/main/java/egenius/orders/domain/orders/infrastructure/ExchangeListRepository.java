package egenius.orders.domain.orders.infrastructure;

import egenius.orders.domain.orders.entity.ExchangeList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeListRepository extends JpaRepository<ExchangeList, Long> {
}
