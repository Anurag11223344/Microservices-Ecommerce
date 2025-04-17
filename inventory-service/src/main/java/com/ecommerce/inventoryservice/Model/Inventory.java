package com.ecommerce.inventoryservice.Model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "inventory")
public class Inventory {
    @Id
    private String id;
    private String productName;
    private int quantity;
}
