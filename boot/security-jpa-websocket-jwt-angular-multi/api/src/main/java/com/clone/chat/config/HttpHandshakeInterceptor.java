package com.clone.chat.config;

import com.clone.chat.domain.User;
import com.clone.chat.service.WebSocketManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

public class HttpHandshakeInterceptor implements HandshakeInterceptor {

    private final WebSocketManagerService webSocketManagerService;


    public HttpHandshakeInterceptor(WebSocketManagerService webSocketManagerService) {
        this.webSocketManagerService = webSocketManagerService;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map attributes) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
//            HttpSession session = servletRequest.getServletRequest().getSession();
//            String id = session.getId();
//            String id = request.getURI().getPath().split("/")[3];
//            attributes.put("sessionId", id);
//            webSocketManagerService.putUser(id, null);
        }
        return true;
    }

    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception ex) {
    }
}
