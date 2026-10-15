package com.gokul.artgallery.controller;

import com.gokul.artgallery.model.Artwork;
import com.gokul.artgallery.model.Order;
import com.gokul.artgallery.repository.ArtworkRepository;
import com.gokul.artgallery.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ArtworkRepository artworkRepository;

    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody Map<String, Long> body, Authentication auth) {
        Artwork artwork = artworkRepository.findById(body.get("artworkId")).orElse(null);
        if (artwork == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Artwork not found"));
        }
        Order order = new Order();
        order.setUserEmail(auth.getName());
        order.setArtwork(artwork);
        order.setStatus("PLACED");
        order.setOrderedAt(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(orderRepository.save(order));
    }

    @GetMapping("/my")
    public List<Order> myOrders(Authentication auth) {
        return orderRepository.findByUserEmailOrderByOrderedAtDesc(auth.getName());
    }
}