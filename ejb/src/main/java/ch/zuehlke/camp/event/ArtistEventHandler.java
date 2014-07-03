package ch.zuehlke.camp.event;

import javax.enterprise.event.Observes;

import ch.zuehlke.camp.domain.Artist;
import ch.zuehlke.camp.service.annotation.Add;

public class ArtistEventHandler {

	public void onArtistAdd(@Observes @Add Artist artist) {
		System.out.println("Created new artist: " + artist);
	}
}
