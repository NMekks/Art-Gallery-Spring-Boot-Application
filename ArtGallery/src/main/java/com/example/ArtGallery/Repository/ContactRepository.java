package com.example.ArtGallery.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ArtGallery.Entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long>{

	public Contact findByArtistId(Long artistId);

}
