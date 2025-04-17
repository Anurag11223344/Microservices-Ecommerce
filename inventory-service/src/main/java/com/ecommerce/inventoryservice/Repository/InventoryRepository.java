package com.ecommerce.inventoryservice.Repository;

import com.ecommerce.inventoryservice.Model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends MongoRepository<Inventory, String> {
    Optional<Inventory> findByProductName(String productName);
}
