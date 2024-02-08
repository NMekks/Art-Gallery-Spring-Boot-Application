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
import com.example.ArtGallery.Repository.ContactRepository;

@RestController
@RequestMapping("artists")
public class ArtistController {

	@Autowired
	ArtistRepository artistRep;

	@Autowired
	ArtworkRepository artworkRep;

	@Autowired
	ContactRepository contRep;

	//the same function which returns the result in json format is down below
	@GetMapping
	public String getArtists() {
		String result = "";

		for (Artist a : artistRep.findAll()) {
			result += "Artist ID: " + a.getId() + "<br>" + a.getName() + " " + a.getSurname() + "<br>"
					+ "Contact Information: " + a.getContact().getEmail() + " - " + a.getContact().getPhoneNum()
					+ "<br>" + "Artworks:<br>";

			for (Artwork ar : a.getArtworks()) {
				result += ar.getArtwork() + " - " + ar.getCategory() + "<br>";
			}
			result += "<br><br>";
		}
		return result += "<br>";
	}

	//the same function which returns the result in json format is down below
	@GetMapping("{id}")
	public StringBuilder getArtist(@PathVariable Long id) {
		Artist a = artistRep.findById(id).get();
		
		StringBuilder result = new StringBuilder("Artist ID: "+a.getId()+"<br>"+ a.getName() + " " + a.getSurname() + "<br>" +
				"<br>Contact Information:<br>" + a.getContact().getEmail() + " - "
				+ a.getContact().getPhoneNum() +"<br><br>Artworks:<br>");

		artistRep.findById(id).get().getArtworks()
				.forEach(ar -> result
						.append(ar.getArtwork() + " " + ar.getCategory() + "<br>"));

		return result;
	}

	//the same function which returns the result in json format is down below
	@GetMapping("artworks/{artId}")
	public String getArtistByArtworkId(@PathVariable Long artId) {
		String result = "";
		Artwork artwork = artworkRep.findById(artId).get();
		for(Artist a : artistRep.findByArtworksId(artId)) {
			result += "<br>Artist ID: "+ a.getId()+"<br>"+a.getName()+" "+a.getSurname()+"<br>";
		}
		return "Artwork ID: "+artwork.getId()+"<br>"+result;			
	}

	@PostMapping("save")
	public String saveArtist(@RequestBody Artist arti) {
		artistRep.save(arti);
		return "Artist Saved Successfully!";
	}

	@GetMapping("assignArtist/{artworkId}/{artiId}")
	public String assignArtistToArtwork(@PathVariable Long artworkId, @PathVariable Long artiId) {
		Artwork artwork = artworkRep.findById(artworkId).get();
		artwork.setArtist(artistRep.findById(artiId).get());
		artworkRep.save(artwork);
		return "Artist Assigned To Artwork Successfully!";
	}

	@PutMapping("update/{artistId}")
	public String updateArtistInfo(@RequestBody Artist artist, @PathVariable Long artistId) {
		Artist arti = artistRep.findById(artistId).get();
		arti.setName(artist.getName());
		arti.setSurname(artist.getSurname());
		artistRep.save(arti);
		return "Artist Information Updated Successfully";
	}

	@DeleteMapping("remove/{id}")
	public String deleteArtist(@PathVariable Long id) {
		artistRep.deleteById(id);
		return "Artist Deleted Successfully!";
	}

}

//----------------------------------------------
/*
 * @GetMapping
 * public List<Artist> getArtists(){
 * return artistRep.findAll();
 * }
 */
//----------------------------------------------
/*
 * @GetMapping("{id}")
 * public Optional<Artist> getArtist(@PathVariable Long id){
 * return artistRep.findById(id);
 * }
 */
//----------------------------------------------
/*
 * @GetMapping("artworks/{artId}") public List<Artist>
 * getArtistsByArtworksId(@PathVariable Long artId){ return
 * artistRep.findByArtworksId(artId);
 * }
 */