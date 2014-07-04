package com.zuehlke.fro.websocket.client;

import java.io.IOException;
import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public class WebSocketClient {
	private static Object waitLock = new Object();

	@OnMessage
	public void onMessage(String message) {
		System.out.println("Received msg: " + message);
	}

	@OnOpen
	public void onOpen(Session session) {
		System.out.println("Opening new client session...");
	}

	private static void wait4TerminateSignal() {
		synchronized (waitLock) {
			try {
				waitLock.wait();
			} catch (InterruptedException e) {
			}
		}
	}

	private static void processInput(Session session) {
		try {
			StringBuilder builder = new StringBuilder();
			char c;
			while ((c = (char) System.in.read()) != '\n') {
				builder.append(c);
			}
			// builder.append(" from WebSocketClient.class.toString()");
			String input = builder.toString();
			sendMessage(session, input);
			processInput(session);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void sendMessage(Session session, String message) {
		try {
			session.getBasicRemote().sendText(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		WebSocketContainer container = null;//
		Session session = null;
		try {
			// Tyrus is plugged via ServiceLoader API.
			container = ContainerProvider.getWebSocketContainer();
			session = container.connectToServer(WebSocketClient.class, URI.create("ws://localhost:8080/webSocketServer/chat"));
			System.out.println("Connected to server...");
			// wait4TerminateSignal();
			processInput(session);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
