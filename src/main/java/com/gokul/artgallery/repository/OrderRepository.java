package com.gokul.artgallery.repository;

import com.gokul.artgallery.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserEmailOrderByOrderedAtDesc(String userEmail);
}