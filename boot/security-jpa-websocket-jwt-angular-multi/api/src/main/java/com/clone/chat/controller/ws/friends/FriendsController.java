package com.clone.chat.controller.ws.friends;

import com.clone.chat.annotation.ModelAttributeMapping;
import com.clone.chat.code.MsgCode;
import com.clone.chat.controller.api.ApiController;
import com.clone.chat.domain.User;
import com.clone.chat.domain.base.UserBase;
import com.clone.chat.exception.BusinessException;
import com.clone.chat.model.UserToken;
import com.clone.chat.model.view.json.JsonViewApi;
import com.clone.chat.repository.UserRepository;
import com.clone.chat.service.FriendService;
import com.clone.chat.service.WebSocketManagerService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller("ws-friends-controller")
public class FriendsController {
    public static final String URI_PREFIX = "/friends";

    @Autowired
    private WebSocketManagerService webSocketManagerService;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    FriendService friendService;

//    @Autowired
//    SimpMessageTemplate


    @MessageMapping(URI_PREFIX)
    @SendToUser("/queue"+URI_PREFIX)
    @JsonView({JsonViewApi.class})
    //@Header("simpSessionId") String sessionId,
    public List<User> processMessageFromClient(Principal principal, SimpMessageHeaderAccessor simpMessageHeaderAccessor) throws Exception {
//        String name = new Gson().fromJson(message, Map.class).get("name").toString();
        //messagingTemplate.convertAndSendToUser(principal.getName(), "/queue/reply", name);
        Optional<UserToken> userToken = webSocketManagerService.getUser(simpMessageHeaderAccessor);
        UserToken user = userToken.orElseThrow(() -> new BusinessException(MsgCode.ERROR_AUTH));
//        user.orElseThrow(() -> new NoSuchElementException("no Such"));
//        return friendRepository.findByUserId(user.get().getId());
        User data = friendService.findFriends(user.getId());
        return data.getFriends();
    }
}
