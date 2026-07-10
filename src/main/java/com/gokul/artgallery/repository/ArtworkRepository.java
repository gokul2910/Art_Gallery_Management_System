package com.gokul.artgallery.repository;

import com.gokul.artgallery.model.Artwork;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtworkRepository extends JpaRepository<Artwork, Long> {
}