package com.example.ArtGallery.Entity;

import java.util.List;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Artist")
public class Artist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name="FirstName")
	String name;
	
	@Column(name="LastName")
	String surname;
	
	@OneToOne(cascade = CascadeType.ALL)
	// @JsonIgnore
	@JsonManagedReference //used JsonManagedReference + JsonBackReference to solve the infinite display issue
	Contact contact;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "artist")
	List<Artwork> artworks;
	
	public Artist(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public List<Artwork> getArtworks() {
		return artworks;
	}
	
	public void setArtworks(List<Artwork> artworks) {
		this.artworks = artworks;
	}


}
