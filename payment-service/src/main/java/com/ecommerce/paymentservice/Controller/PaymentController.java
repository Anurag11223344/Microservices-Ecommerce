package com.ecommerce.paymentservice.Controller;

import com.ecommerce.paymentservice.Model.Payment;
import com.ecommerce.paymentservice.Repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentRepository paymentRepository;

    @GetMapping("/all")
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @GetMapping("/status/{productName}")
    public List<Payment> getPaymentsByProduct(@PathVariable String productName) {
        return paymentRepository.findAll().stream()
                .filter(p -> p.getProductName().equalsIgnoreCase(productName))
                .toList();
    }
}
