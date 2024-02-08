package com.example.ArtGallery.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ArtGallery.Entity.Artwork;

public interface ArtworkRepository extends JpaRepository<Artwork, Long>{

	public List<Artwork> findByArtistId(Long artiId);

}
