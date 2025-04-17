package com.ecommerce.inventoryservice.Controller;

import com.ecommerce.inventoryservice.Model.Inventory;
import com.ecommerce.inventoryservice.Service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    // 1. Check if stock available
    @GetMapping("/check/{productName}/{quantity}")
    public String checkStock(@PathVariable String productName, @PathVariable int quantity) {
        return inventoryService.isInStock(productName, quantity) ? "In Stock" : "Out of Stock";
    }

    // 2. Add new product to inventory
    @PostMapping("/add")
    public String addProduct(@RequestBody Inventory inventory) {
        inventoryService.addInventory(inventory);
        return "Product Added Successfully!";
    }

    // 3. Update quantity of product
    @PutMapping("/update/{productName}")
    public String updateProduct(@PathVariable String productName, @RequestBody Inventory inventory) {
        inventoryService.updateInventory(productName, inventory.getQuantity());
        return "Product Updated Successfully!";
    }

    // 4. Delete product from inventory
    @DeleteMapping("/delete/{productName}")
    public String deleteProduct(@PathVariable String productName) {
        inventoryService.deleteInventory(productName);
        return "Product Deleted Successfully!";
    }

    // 5. View All Products
    @GetMapping("/all")
    public List<Inventory> getAllInventory() {
        return inventoryService.getAllInventory();
    }
}
