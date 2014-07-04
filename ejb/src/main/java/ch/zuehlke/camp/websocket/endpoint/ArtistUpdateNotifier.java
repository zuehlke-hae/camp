package ch.zuehlke.camp.websocket.endpoint;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import ch.zuehlke.camp.domain.Artist;
import ch.zuehlke.camp.service.annotation.Add;

@ServerEndpoint("/socket/artist")
@SessionScoped
public class ArtistUpdateNotifier implements Serializable {

	private static final long serialVersionUID = -8313555865929573833L;
	private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());
	
	@OnOpen
	public String open(Session session) {
		sessions.add(session);
		System.out.println("Opened new websocket connection with id " + session.getId());
		return "Welcome to ArtistUpdateNotifier websocket";
	}
	
	@OnClose
	public void close(CloseReason closeReason) {
		System.out.println("Closed websocket connection. " 
				+ ". Reason code " + closeReason.getCloseCode() 
				+ " and reason '" + closeReason.getReasonPhrase() + "'");
	}
	
	
	public void onArtistAdd(@Observes @Add Artist artist) {
		for (Session session : sessions) {
			if(session.isOpen()) {
				session.getAsyncRemote().sendText("New artist inserted: " + artist.toString());				
			}
		}
	}
	
}
