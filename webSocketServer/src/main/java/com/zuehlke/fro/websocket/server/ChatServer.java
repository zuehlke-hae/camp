package com.zuehlke.fro.websocket.server;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat")
public class ChatServer {

	private static Logger logger = Logger.getLogger(ChatServer.class.getName());

	private static Queue<Session> queue = new ConcurrentLinkedQueue<Session>();
	private static Thread adminUserThread;
	static {
		adminUserThread = new Thread() {
			@Override
			public void run() {
				while (true) {
					sendAll("Hello from Server:-)");
					try {
						sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		};
		adminUserThread.start();
	}

	@OnMessage
	public void receiveMessage(Session session, String message) {
		System.out.println("received msg " + message + " from " + session.getId());
		sendAll(message);
	}

	@OnOpen
	public void open(Session session) {
		queue.add(session);
		logger.info("opening chat session");
	}

	@OnClose
	public void close(Session session, CloseReason closeReason) {
		queue.remove(session);
		logger.info("closing chat session... reason: " + closeReason.getCloseCode().toString());
	}

	@OnError
	public void error(Session session, Throwable ex) {
		queue.remove(session);
		logger.log(Level.SEVERE, "onError", ex);
	}

	private static void sendAll(String msg) {
		try {
			/* Send the new rate to all open WebSocket sessions */
			ArrayList<Session> closedSessions = new ArrayList<>();
			for (Session session : queue) {
				if (!session.isOpen()) {
					logger.severe("Closed session: " + session.getId());
					closedSessions.add(session);
				} else {
					session.getBasicRemote().sendText(msg);
				}
			}
			queue.removeAll(closedSessions);
			logger.info("Sending " + msg + " to " + queue.size() + " clients");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
