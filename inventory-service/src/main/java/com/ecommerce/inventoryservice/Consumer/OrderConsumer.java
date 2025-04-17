package com.ecommerce.inventoryservice.Consumer;

import com.ecommerce.inventoryservice.Event.OrderEvent;
import com.ecommerce.inventoryservice.Service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderConsumer {

    private final InventoryService inventoryService;
    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "order-queue")
    public void consumeOrder(OrderEvent event) {
        boolean inStock = inventoryService.isInStock(event.getProductName(), event.getQuantity());
//        event.setStatus(inStock ? "SUCCESS" : "FAILED");
        if (inStock) {
            inventoryService.reduceStock(event.getProductName(), event.getQuantity());
            event.setStatus("SUCCESS");
        } else {
            event.setStatus("FAILED");
        }


        rabbitTemplate.convertAndSend("payment-queue", event);
    }
}
