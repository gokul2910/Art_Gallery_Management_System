package com.gokul.artgallery.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "artworks")
@Data
public class Artwork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String artistName;

    private String description;

    private Double price;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}