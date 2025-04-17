package com.ecommerce.paymentservice.Service;

import com.ecommerce.paymentservice.Event.OrderEvent;
import com.ecommerce.paymentservice.Model.Payment;
import com.ecommerce.paymentservice.Repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final RabbitTemplate rabbitTemplate;

    public void processPayment(OrderEvent event) {
        Payment payment = new Payment();
        payment.setProductName(event.getProductName());
        payment.setQuantity(event.getQuantity());

        // Dummy logic → Accept only SUCCESS from Inventory
        if ("SUCCESS".equals(event.getStatus())) {
            payment.setPaymentStatus("PAID");
            event.setStatus("COMPLETED");
        } else {
            payment.setPaymentStatus("FAILED");
            event.setStatus("FAILED");
        }

        paymentRepository.save(payment);

        // Send back to Order
        rabbitTemplate.convertAndSend("payment-queue", event);
    }
}
