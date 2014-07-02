package ch.zuehlke.camp.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.zuehlke.camp.domain.Artist;
import ch.zuehlke.camp.service.ArtistService;
import ch.zuehlke.camp.service.annotation.MockQualifier;

@Path("artist")
public class ArtistResource {

	@Inject
	@MockQualifier
	private ArtistService artistService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Artist> getArtists() {
		return artistService.getArtists();
	}
	
	// Use RESTClient Add-On (Firefox, Chrome) for example
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Artist postArtis(Artist artist) {
		return artistService.createArtist(artist);
	}
}
