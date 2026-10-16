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
    public ResponseEntity<?> placeOrder(@RequestBody Map<String, Object> body, Authentication auth) {
        Object idObj = body.get("artworkId");
        if (idObj == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "artworkId is required"));
        }
        Artwork artwork = artworkRepository.findById(Long.valueOf(idObj.toString())).orElse(null);
        if (artwork == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Artwork not found"));
        }

        String fullName = str(body, "fullName");
        String phone = str(body, "phone");
        String address = str(body, "address");
        String city = str(body, "city");
        String pincode = str(body, "pincode");
        String paymentMethod = str(body, "paymentMethod");

        if (fullName.isEmpty() || phone.isEmpty() || address.isEmpty()
                || city.isEmpty() || pincode.isEmpty() || paymentMethod.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "All fields are required"));
        }

        Order order = new Order();
        order.setUserEmail(auth.getName());
        order.setArtwork(artwork);
        order.setStatus("PLACED");
        order.setOrderedAt(LocalDateTime.now());
        order.setFullName(fullName);
        order.setPhone(phone);
        order.setAddress(address);
        order.setCity(city);
        order.setPincode(pincode);
        order.setPaymentMethod(paymentMethod);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderRepository.save(order));
    }

    private String str(Map<String, Object> body, String key) {
        Object v = body.get(key);
        return v == null ? "" : v.toString().trim();
    }

    @GetMapping("/my")
    public List<Order> myOrders(Authentication auth) {
        return orderRepository.findByUserEmailOrderByOrderedAtDesc(auth.getName());
    }
}