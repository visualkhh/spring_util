package com.clone.chat.config;

import com.clone.chat.model.UserToken;
import com.clone.chat.repository.UserRepository;
import com.clone.chat.service.TokenService;
import com.clone.chat.service.WebSocketManagerService;
import io.jsonwebtoken.ClaimJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.util.MultiValueMap;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Optional;

@Configuration
@Slf4j
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    @Autowired
    WebSocketManagerService webSocketManagerService;


    @Autowired
    TokenService tokenService;


    @Autowired
    UserRepository userRepository;

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {


                MessageHeaders headers = message.getHeaders();
                StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
                MultiValueMap<String, String> multiValueMap = headers.get(StompHeaderAccessor.NATIVE_HEADERS,MultiValueMap.class);
                if (StompCommand.SUBSCRIBE.equals(accessor.getCommand()) || StompCommand.MESSAGE.equals(accessor.getCommand()) || StompCommand.SEND.equals(accessor.getCommand())) {
                    String token = multiValueMap.getFirst(HttpHeaders.AUTHORIZATION);
                    try {
                        UserToken userToken = tokenService.parserJwtToUserToken(token);
    //                    String sessionId = (String) accessor.getSessionAttributes().get("sessionId");
                        String sessionId = accessor.getSessionId();
                        Optional<UserToken> sessionUser = webSocketManagerService.getUser(sessionId);
                        if (!sessionUser.isPresent()) {
                            webSocketManagerService.putUser(sessionId, userToken);
                        }
                    } catch (ClaimJwtException e) { // ExpiredJwtException
                        webSocketManagerService.removeByUserId(e.getClaims().getSubject());
                        throw e;
                    }
                }

//                StompHeaderAccessor accessor =
//                        MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
//                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
////                    accessor.
////                    Authentication user = ... ; // access authentication header(s)
////                    accessor.setUser(user);
//                }
                return message;
            }
        });
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic", "/queue");
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket")
                .setAllowedOrigins("*")
//                .addInterceptors(new HttpHandshakeInterceptor(webSocketManagerService))
//                .setHandshakeHandler(new AssignPrincipalHandshakeHandler())
//                .setHandshakeHandler(new DefaultHandshakeHandler(){
//                    public boolean beforeHandshake(
//                            ServerHttpRequest request,
//                            ServerHttpResponse response,
//                            WebSocketHandler wsHandler,
//                            Map attributes) throws Exception {
//
//                        if (request instanceof ServletServerHttpRequest) {
//                            ServletServerHttpRequest servletRequest
//                                    = (ServletServerHttpRequest) request;
//                            HttpSession session = servletRequest
//                                    .getServletRequest().getSession();
//                            attributes.put("sessionId", session.getId());
//                        }
//                        return true;
//                    }
//                })
                .withSockJS();
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        log.info("Received a new web socket connection");
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String sessionId = headers.getSessionId();
        log.debug("sessionId is " + sessionId);
        if(null!=headers.getUser()) {
            String username = headers.getUser().getName(); // headers.getUser() is null
            log.debug("username is " + username);
        }
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
//        String sessionId = (String) headerAccessor.getSessionAttributes().get("sessionId");

        webSocketManagerService.removeUser(event.getSessionId());

//        if(username != null) {
//            log.info("User Disconnected : " + username);

//            ChatMessage chatMessage = new ChatMessage();
//            chatMessage.setType(MessageType.LEAVE);
//            chatMessage.setSender(username);

//            messagingTemplate.convertAndSend("/topic/public", chatMessage);
//        }
    }

//    @EventListener
//    private void handleSessionConnect(SessionConnectEvent event) {
//        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
//        String sessionId = headers.getSessionId();
//        log.debug("sessionId is " + sessionId);
//        String username = headers.getUser().getName(); // headers.getUser() is null
//        log.debug("username is " + username);
//    }
//
//    @EventListener
//    private void handleSessionConnected(SessionConnectEvent event) {
//        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
//        String sessionId = headers.getSessionId();
//        log.debug("sessionId is " + sessionId);
//        String username = headers.getUser().getName(); // headers.getUser() is null
//        log.debug("username is " + username);
//    }
//
//    @EventListener
//    private void handleSubscribeEvent(SessionSubscribeEvent event) {
//        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
//        String sessionId = headers.getSessionId();
//        log.debug("sessionId is " + sessionId);
//        String subscriptionId = headers.getSubscriptionId();
//        log.debug("subscriptionId is " + subscriptionId);
//        String username = headers.getUser().getName(); // headers.getUser() is null
//        log.debug("username is " + username);
//    }
//
//    @EventListener
//    private void handleUnsubscribeEvent(SessionUnsubscribeEvent event) {
//        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
//        String sessionId = headers.getSessionId();
//        log.debug("sessionId is " + sessionId);
//        String subscriptionId = headers.getSubscriptionId();
//        log.debug("subscriptionId is " + subscriptionId);
//        String username = headers.getUser().getName(); // headers.getUser() is null
//        log.debug("username is " + username);
//    }
//
//    @EventListener
//    private void handleSessionDisconnect(SessionDisconnectEvent event) {
//        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
//        log.debug("sessionId is " + event.getSessionId());
//        String username = headers.getUser().getName(); // headers.getUser() is null
//        log.debug("username is " + username);
//    }

    //////////////////////////--
//    @Override
//    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
//        registration.addDecoratorFactory(new WebSocketHandlerDecoratorFactory() {
//            @Override
//            public WebSocketHandler decorate(final WebSocketHandler handler) {
//                return new WebSocketHandlerDecorator(handler) {
//
//                    @Override
//                    public void afterConnectionEstablished(final WebSocketSession session) throws Exception {
//
//                        // We will store current user's session into WebsocketSessionHolder after connection is established
//                        if(null!=session.getPrincipal()) {
//                            String username = session.getPrincipal().getName();
//                            WebsocketSessionHolder.addSession(username, session);
//                        }
//
//                        super.afterConnectionEstablished(session);
//                    }
//
//                    @Override
//                    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
//                        // We will store current user's session into WebsocketSessionHolder after connection is established
//                        if(null!=session.getPrincipal()) {
//                            String username = session.getPrincipal().getName();
//                            WebsocketSessionHolder.closeSessions(username);
//                        }
//                        super.afterConnectionClosed(session, closeStatus);
//                    }
//                };
//            }
//        });
//    }








//    @Override
//    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
//        registry.addDecoratorFactory(new WebSocketHandlerDecoratorFactory() {
//            @Override
//            public WebSocketHandler decorate(WebSocketHandler handler) {
//                return new WebSocketHandlerDecorator(handler) {
//                    @Override
//                    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//                        log.info("-");
//                        super.afterConnectionEstablished(session);
//                    }
//
//                    @Override
//                    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
//                        log.info("-");
//
//                    }
//
//                    @Override
//                    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
//
//                        log.info("-");
//                    }
//
//                    @Override
//                    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
//
//                        log.info("-");
//                    }
//
//                    @Override
//                    public boolean supportsPartialMessages() {
//                        return false;
//                    }
//                };
//            }
//        });
//    }

//    @Override
//    public void configureClientInboundChannel(ChannelRegistration registration) {
//        registration.interceptors(new ChannelInterceptor() {
//        });
//    }
}
