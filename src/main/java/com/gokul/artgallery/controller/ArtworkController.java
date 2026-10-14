package com.gokul.artgallery.controller;

import com.gokul.artgallery.model.Artwork;
import com.gokul.artgallery.repository.ArtworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artworks")
public class ArtworkController {

    @Autowired
    private ArtworkRepository artworkRepository;

    @GetMapping
    public List<Artwork> getAllArtworks() {
        return artworkRepository.findAll();
    }

    @GetMapping("/{id}")
    public Artwork getArtworkById(@PathVariable Long id) {
        return artworkRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Artwork createArtwork(@RequestBody Artwork artwork) {
        return artworkRepository.save(artwork);
    }

    @PutMapping("/{id}")
    public Artwork updateArtwork(@PathVariable Long id, @RequestBody Artwork updatedArtwork) {
        updatedArtwork.setId(id);
        return artworkRepository.save(updatedArtwork);
    }

    @DeleteMapping("/{id}")
    public String deleteArtwork(@PathVariable Long id) {
        artworkRepository.deleteById(id);
        return "Artwork deleted successfully";
    }
}