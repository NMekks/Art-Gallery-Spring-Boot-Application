package com.example.ArtGallery.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Contact Information")
public class Contact {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	@Column(name="Email")
	String email;
	
	@Column(name="Phone Number")
	Long phoneNum;
	
	@OneToOne(mappedBy="contact")
	@JsonBackReference //used JsonBackReference to solve the infinite output issue
	Artist artist;
	
	public Contact() {
		
	}
	
	public Contact(String email, Long phoneNum) {
		this.email = email;
		this.phoneNum = phoneNum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(Long phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

}
