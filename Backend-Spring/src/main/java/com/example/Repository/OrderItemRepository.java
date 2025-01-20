package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
  
}
