package com.ecommerce.orderservice.Consumer;

import com.ecommerce.orderservice.Event.OrderEvent;
import com.ecommerce.orderservice.Model.Order;
import com.ecommerce.orderservice.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentConsumer {

    private final OrderRepository orderRepository;

    @RabbitListener(queues = "payment-queue")
    public void consumePayment(OrderEvent event) {
        Order order = orderRepository.findAll()
                .stream()
                .filter(o -> o.getProductName().equals(event.getProductName()))
                .findFirst()
                .orElseThrow();

        order.setOrderStatus(event.getStatus());
        orderRepository.save(order);
    }
}
