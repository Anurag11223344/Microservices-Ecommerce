package com.ecommerce.paymentservice.Consumer;

import com.ecommerce.paymentservice.Event.OrderEvent;
import com.ecommerce.paymentservice.Service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryConsumer {

    private final PaymentService paymentService;

    @RabbitListener(queues = "payment-queue")
    public void consumeInventoryEvent(OrderEvent event) {
        paymentService.processPayment(event);
    }
}
