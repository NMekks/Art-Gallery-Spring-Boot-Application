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
import com.example.ArtGallery.Entity.Artwork;
import com.example.ArtGallery.Repository.ArtistRepository;
import com.example.ArtGallery.Repository.ArtworkRepository;

@RestController
@RequestMapping("artworks")
public class ArtworkController {

	@Autowired
	ArtworkRepository artworkRep;

	@Autowired
	ArtistRepository artistRep;
	
	//the same function which returns the result in json format is down below
	@GetMapping
	public String getArtworks() {
		String result ="";
		
		for(Artwork ar : artworkRep.findAll()) {
			result += ar.getArtwork()+" - "+ar.getCategory()+ ". Artist ID:" + ar.getArtist().getId() +"<br>";
		}
		return "Artworks:<br>"+ result;
	}
	
	//the same function which returns the result in json format is down below
	@GetMapping("{id}")
	public String getArtwork(@PathVariable Long id) {
		Artwork ar = artworkRep.findById(id).get();
		return "Artwork ID: "+ar.getId()+"<br>"+ar.getArtwork()+"  -  "+ar.getCategory()+"<br>";
	}
	
	//the same function which returns the result in json format is down below
	@GetMapping("artist/{artistId}")
	public String getArtworkByArtistId(@PathVariable Long artistId) {
		String result = "";
		Artist artist = artistRep.findById(artistId).get();
		for(Artwork ar : artworkRep.findByArtistId(artistId)) {
			result += ar.getArtwork()+"  -  "+ar.getCategory()+"<br>";
		}
		return "Artist ID: "+artist.getId()+"<br>"+"<br>Artworks:<br>"+result;			
	}
	
	@PostMapping("save")
	public String saveArtwork(@RequestBody Artwork artworkId){
		artworkRep.save(artworkId);
		return "Artwork Saved Successfully!";
	}

	@PutMapping("update/{artworkId}")
	public String updateArtworkInfo(@RequestBody Artwork artwork, @PathVariable Long artworkId) {
		Artwork art = artworkRep.findById(artworkId).get();
		art.setArtwork(artwork.getArtwork());
		art.setCategory(artwork.getCategory());
		artworkRep.save(art);
		return "Artwork Information Updated Successfully";
	}

	 @DeleteMapping("remove/{artId}")
	  public String removeArtwork(@PathVariable Long artId) {
		 artworkRep.deleteById(artId);
		 return "Artwork Deleted Succesfully";
	 }
	 

}
//----------------------------------------
/*@GetMapping
public List<Artwork> getArtworks() {
	return artworkRep.findAll();
}*/
//----------------------------------------
/*
@GetMapping("{artId}")
public Optional<Artwork> getArtwork(@PathVariable Long artId) {
	return artworkRep.findById(artId);
}
*/
//----------------------------------------
/*
@GetMapping("artists/{artiId}")
public List<Artwork> getArtworkByArtistId(@PathVariable Long artiId) {
	return artworkRep.findByArtistId(artiId);
}*/
