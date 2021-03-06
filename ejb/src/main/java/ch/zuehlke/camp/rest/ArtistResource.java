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

@Path("artist")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArtistResource {

	@Inject
	// Uncomment this line to switch to the service mock
//	@MockQualifier
	private ArtistService artistService;
	
	@GET
	public List<Artist> getArtists() {
		return artistService.getArtists();
	}
	
	// Use RESTClient Add-On (Firefox, Chrome) for example
	// Important: Set Content-Type header parameter to application/json
	@POST
	public Artist postArtis(Artist artist) {
		return artistService.createArtist(artist);
	}
}
