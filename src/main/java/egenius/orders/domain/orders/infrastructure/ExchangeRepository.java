package egenius.orders.domain.orders.infrastructure;

import egenius.orders.domain.orders.entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepository extends JpaRepository<Exchange, Long> {
}
