package com.ecommerce.orderservice.Repository;

import com.ecommerce.orderservice.Model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
}
