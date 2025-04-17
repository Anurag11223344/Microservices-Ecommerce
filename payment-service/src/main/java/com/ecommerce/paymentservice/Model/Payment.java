package com.ecommerce.paymentservice.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "payments")
public class Payment {
    @Id
    private String id;
    private String productName;
    private int quantity;
    private String paymentStatus; // PAID or FAILED
}
