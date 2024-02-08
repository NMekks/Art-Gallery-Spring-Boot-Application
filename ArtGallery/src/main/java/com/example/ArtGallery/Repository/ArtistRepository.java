package com.example.ArtGallery.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ArtGallery.Entity.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Long>{

	public List<Artist> findByArtworksId(Long artId);


}
