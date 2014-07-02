package ch.zuehlke.camp.service;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import ch.zuehlke.camp.domain.Artist;
import ch.zuehlke.camp.service.annotation.MockQualifier;

@MockQualifier
public class ArtistServiceMock implements ArtistService {

	private static List<Artist> ARTIST_FIXTURES = new ArrayList<Artist>();
	
	static {
		ARTIST_FIXTURES.add(new Artist(24, "The Doors", Lists.newArrayList("Jim Morrison", "Robby Krieger", "Ray Manzarek", "John Densmore")));
		ARTIST_FIXTURES.add(new Artist(64, "Pink Floyd", Lists.newArrayList("Roger Waters", "David Gilmour", "Richard Wright", "Nick Mason")));
		ARTIST_FIXTURES.add(new Artist(88, "Emerson, Lake and Palmer", Lists.newArrayList("Keith Emerson", "Greg Lake", "Carl Palmer")));
	}
	
	@Override
	public List<Artist> getArtists() {
		return ARTIST_FIXTURES;
	}

	@Override
	public Artist createArtist(Artist artist) {
		return new Artist(createRandomId(), artist.getName(), artist.getMembers());
	}

	private int createRandomId() {
		return (int) Math.floor(Math.random() * 100);
	}

}
