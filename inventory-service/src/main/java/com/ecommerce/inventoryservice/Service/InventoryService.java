package com.ecommerce.inventoryservice.Service;

import com.ecommerce.inventoryservice.Model.Inventory;
import com.ecommerce.inventoryservice.Repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    // Check if product is in stock
    public boolean isInStock(String productName, int quantity) {
        return inventoryRepository.findByProductName(productName)
                .map(inventory -> inventory.getQuantity() >= quantity)
                .orElse(false);
    }

    // Add new product to Inventory
    public void addInventory(Inventory inventory) {
        inventoryRepository.save(inventory);
    }

    // Update quantity of existing product
    public void updateInventory(String productName, int quantity) {
        Inventory inventory = inventoryRepository.findByProductName(productName)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        inventory.setQuantity(quantity);
        inventoryRepository.save(inventory);
    }

    // Delete product from Inventory
    public void deleteInventory(String productName) {
        Inventory inventory = inventoryRepository.findByProductName(productName)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        inventoryRepository.delete(inventory);
    }

    // View all inventory items
    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public void reduceStock(String productName, int quantity) {
        Inventory inventory = inventoryRepository.findByProductName(productName)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        int updatedQty = inventory.getQuantity() - quantity;

        if (updatedQty < 0) {
            throw new RuntimeException("Not enough stock to reduce");
        }

        inventory.setQuantity(updatedQty);
        inventoryRepository.save(inventory);
    }

}
