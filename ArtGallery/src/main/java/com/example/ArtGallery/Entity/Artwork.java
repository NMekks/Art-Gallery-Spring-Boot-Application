package com.example.ArtGallery.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Artwork")
public class Artwork {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name="Artwork")
	String artwork;
	
	@Column(name="Category")
	String category;
	
	@ManyToOne
	@JsonBackReference
	Artist artist;
	
	public Artwork(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getArtwork() {
		return artwork;
	}

	public void setArtwork(String artwork) {
		this.artwork = artwork;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public void removeArtist(Artist a) {
		this.removeArtist(a);
		a.artworks.remove(this);
	}
	
	
	
}
