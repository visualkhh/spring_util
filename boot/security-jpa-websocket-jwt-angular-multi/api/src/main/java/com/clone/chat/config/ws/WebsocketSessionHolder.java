package com.clone.chat.config.ws;

// WebsocketSessionHolder.java

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebsocketSessionHolder {

    static {
        sessions = new HashMap<>();
    }

    // key - username, value - List of user's sessions
    private static Map<String, List<WebSocketSession>> sessions;

    public static void addSession(String username, WebSocketSession session)
    {
        synchronized (sessions) {
            List<WebSocketSession> userSessions = sessions.get(username);
            if (userSessions == null)
                userSessions = new ArrayList<WebSocketSession>();

            userSessions.add(session);
            sessions.put(username, userSessions);
        }
    }

    public static void closeSessions(String username) throws IOException
    {
        synchronized (sessions) {
            List<WebSocketSession> userSessions = sessions.get(username);
            if (userSessions != null)
            {
                for(WebSocketSession session : userSessions) {
                    // I use POLICY_VIOLATION to indicate reason of disconnecting for a client
                    session.close(CloseStatus.POLICY_VIOLATION);
                }
                sessions.remove(username);
            }
        }
    }

    public static Map<String, List<WebSocketSession>> getSessions() {
        return WebsocketSessionHolder.sessions;
    }
}
