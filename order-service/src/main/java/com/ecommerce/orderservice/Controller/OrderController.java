package com.ecommerce.orderservice.Controller;

import com.ecommerce.orderservice.Model.Order;
import com.ecommerce.orderservice.Repository.OrderRepository;
import com.ecommerce.orderservice.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderRepository orderRepository;
    private final OrderService orderService;

    @PostMapping("/place")
    public String placeOrder(@RequestBody Order order) {
        return orderService.placeOrder(order);
    }

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/status/{id}")
    public String getOrderStatus(@PathVariable String id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return "Status: " + order.getOrderStatus();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteOrder(@PathVariable String id) {
        orderRepository.deleteById(id);
        return "Order deleted successfully!";
    }

}
