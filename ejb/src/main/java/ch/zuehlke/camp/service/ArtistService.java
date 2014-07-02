package ch.zuehlke.camp.service;

import java.util.List;

import ch.zuehlke.camp.domain.Artist;

public interface ArtistService {

	public List<Artist> getArtists();
	public Artist createArtist(Artist artist);
}
