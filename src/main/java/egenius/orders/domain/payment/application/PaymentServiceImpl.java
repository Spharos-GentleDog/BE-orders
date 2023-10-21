package egenius.orders.domain.payment.application;

import egenius.orders.domain.payment.infrastructure.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;
}
