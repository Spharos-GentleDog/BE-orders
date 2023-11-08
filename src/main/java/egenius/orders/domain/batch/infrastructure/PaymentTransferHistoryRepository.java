package egenius.orders.domain.batch.infrastructure;

import egenius.orders.domain.batch.entity.PaymentTransferHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTransferHistoryRepository extends JpaRepository<PaymentTransferHistory, Long> {
}
