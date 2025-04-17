package com.ecommerce.orderservice.Service;

import com.ecommerce.orderservice.Event.OrderEvent;
import com.ecommerce.orderservice.Model.Order;
import com.ecommerce.orderservice.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final RabbitTemplate rabbitTemplate;

    public String placeOrder(Order order) {
        // initially set pending
        order.setOrderStatus("PENDING");
        orderRepository.save(order);

        // send event to inventory
        OrderEvent event = new OrderEvent(order.getProductName(), order.getQuantity(), "PENDING");
        rabbitTemplate.convertAndSend("order-queue", event);

        return "Order Placed Successfully!";
    }
}
