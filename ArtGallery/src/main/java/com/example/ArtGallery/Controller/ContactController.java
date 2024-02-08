package com.example.ArtGallery.Controller;

//import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ArtGallery.Entity.Artist;
import com.example.ArtGallery.Entity.Contact;
import com.example.ArtGallery.Repository.ArtistRepository;
import com.example.ArtGallery.Repository.ContactRepository;

@RestController
@RequestMapping("contacts")
public class ContactController {
	
	@Autowired
	ContactRepository contRep;
	
	@Autowired
	ArtistRepository artistRep;
	
	//the same function which returns the result in json format is down below
	@GetMapping
	public String getContacts() {
		String result = "";
		
		for(Contact c : contRep.findAll()) {
			result += "Contact ID: "+ c.getId()+"<br>"+c.getEmail()+" , "+c.getPhoneNum()+" , Artist ID: "+c.getArtist().getId()+"<br><br>";
		}
		return result;	
	}
	
	//the same function which returns the result in json format is down below
	@GetMapping("{id}")
	public String getContact(@PathVariable Long id) {
		Contact c = contRep.findById(id).get();
		return "Contact ID: "+c.getId()+"<br>"+c.getEmail()+"  -  "+c.getPhoneNum()+"<br>";
				
	}
	
	//the same function which returns the result in json format is down below
	@GetMapping("artist/{artistId}")
	public String getContactsByArtistId(@PathVariable Long artistId) {
		Contact c = contRep.findByArtistId(artistId);
		Artist a = artistRep.findById(artistId).get();
		return "Artist ID: "+a.getId()+"<br><br>"+"Contact ID: "+c.getId()+"<br>"+c.getEmail()+"  -  "+c.getPhoneNum()+"<br>";		
	}
	
	@PostMapping("save")
	public String saveContact(@RequestBody Contact cont){
		contRep.save(cont);
		return "Contact Information Saved Successfully!";
	}
	
	@GetMapping("assignContact/{artiId}/{contactId}") //assign a contact to an artist, bc simply we are creating an artist and a contact separately in separate tables, we have to link the foriegn keys together.
	public String assignContact(@PathVariable Long artiId, @PathVariable Long contactId) {
		Artist artist = artistRep.findById(artiId).get();
		artist.setContact(contRep.findById(contactId).get());
		artistRep.save(artist);
		return "Contact Assigned To Artist Successfully";	
	}
	
	@PutMapping("update/{contactId}")
	public String updateContactInfo(@RequestBody Contact contact, @PathVariable Long contactId) {
		Contact cont = contRep.findById(contactId).get();
		cont.setEmail(contact.getEmail());
		cont.setPhoneNum(contact.getPhoneNum());
		contRep.save(cont);
		return "Contact Information Saved Successfully!";
	}

	@DeleteMapping("remove/{id}") //this function deletes the contact id in the artist table and deletes the contact for that contact id in the contacts table, by doing this you are nullifying their relationship and allowing them to be deleted simultaneously
	public String deleteContact(@PathVariable Long id){
		Contact contact = contRep.findById(id).orElse(null); //contact is an object and when we use findById we're trying to find sth from a list which we cant do bc theyre not the same "type", so we use orElse to kind of objectify the thing we are looking for. this allows us to use contacts as an object
		if(!contRep.findById(id).equals(null)){
			contact.getArtist().setContact(null);
			contRep.save(contact);
			contRep.delete(contact);
		}
		return "Deleted Successfully!";
		
	}

}
//-----------------------------------------------
/*@GetMapping
public List<Contact> getContacts(){
	return contRep.findAll();
}*/
//-----------------------------------------------
/*
@GetMapping("{id}")
public Optional<Contact> getContact(@PathVariable Long id){
	return contRep.findById(id);
}*/
//-----------------------------------------------
/*
@GetMapping("artist/{artiId}")
public List<Contact> getContactsByArtistId(@PathVariable Long artiId){
	return contRep.findByArtistId(artiId); //finds all the contact according to their artist ids
}*/
