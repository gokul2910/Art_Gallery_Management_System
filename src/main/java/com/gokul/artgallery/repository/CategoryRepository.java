package com.gokul.artgallery.repository;

import com.gokul.artgallery.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}