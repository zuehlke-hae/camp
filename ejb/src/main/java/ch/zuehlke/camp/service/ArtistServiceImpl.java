package ch.zuehlke.camp.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import ch.zuehlke.camp.domain.Artist;
import ch.zuehlke.camp.jpa.Member;
import ch.zuehlke.camp.service.annotation.Add;
import ch.zuehlke.camp.service.interceptor.Logging;

@Stateless
@Transactional
@Logging
public class ArtistServiceImpl implements ArtistService {

	@PersistenceContext
	EntityManager entityManager;
	
	@Inject
	@Any
	@Add
	private Event<Artist> artistAddedEvent;
	
	@Override
	public List<Artist> getArtists() {
		TypedQuery<ch.zuehlke.camp.jpa.Artist> artistsQuery = entityManager.createQuery("SELECT a FROM Artist a", ch.zuehlke.camp.jpa.Artist.class);
		List<Artist> artists = new ArrayList<Artist>();
		for (ch.zuehlke.camp.jpa.Artist artist : artistsQuery.getResultList()) {
			artists.add(createNewArtistDomainObject(artist));
		}
		return artists;
	}

	@Override
	public Artist createArtist(Artist artist) {
		ch.zuehlke.camp.jpa.Artist newArtistEntity = createArtistEntity(artist);
		
		for (ch.zuehlke.camp.jpa.Member member : newArtistEntity.getMembers()) {
			entityManager.persist(member);
		}
		entityManager.persist(newArtistEntity);
		entityManager.flush();
		
		Artist newArtist = createNewArtistDomainObject(newArtistEntity);
		artistAddedEvent.fire(newArtist);
		return newArtist;
	}

	private Artist createNewArtistDomainObject(
			ch.zuehlke.camp.jpa.Artist newArtistEntity) {
		List<String> memberNames = new ArrayList<String>();
		for (Member member : newArtistEntity.getMembers()) {
			memberNames.add(member.getName());
		}
		return new Artist(newArtistEntity.getId(), newArtistEntity.getName(), memberNames);
	}

	private ch.zuehlke.camp.jpa.Artist createArtistEntity(Artist artist) {
		ch.zuehlke.camp.jpa.Artist newArtistEntity = new ch.zuehlke.camp.jpa.Artist();
		newArtistEntity.setName(artist.getName());
		List<Member> members = new ArrayList<Member>();
		for (String memberName : artist.getMembers()) {
			Member newMemberEntity = new Member();
			newMemberEntity.setName(memberName);
			members.add(newMemberEntity);
		}
		
		newArtistEntity.setMembers(members);
		return newArtistEntity;
	}

}
