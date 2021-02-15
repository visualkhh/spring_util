package com.clone.chat.controller.ws.messages;

import com.clone.chat.code.MsgCode;
import com.clone.chat.controller.ws.messages.model.RequestMessage;
import com.clone.chat.domain.Message;
import com.clone.chat.domain.RoomMessage;
import com.clone.chat.domain.UserMessage;
import com.clone.chat.exception.BusinessException;
import com.clone.chat.model.UserToken;
import com.clone.chat.model.view.json.JsonViewApi;
import com.clone.chat.repository.MessageRepository;
import com.clone.chat.repository.UserMessageRepository;
import com.clone.chat.repository.UserRepository;
import com.clone.chat.repository.UserRoomRepository;
import com.clone.chat.service.WebSocketManagerService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller("ws-messages-controller")
public class MessagesController {
    public static final String URI_PREFIX = "/messages";

    @Autowired
    private WebSocketManagerService webSocketManagerService;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoomRepository userRoomRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserMessageRepository userMessageRepository;

    @MessageMapping(URI_PREFIX+"/{userId}/send")
    public void createRoom(RequestMessage message, @DestinationVariable("userId") String toUserId, Principal principal, SimpMessageHeaderAccessor simpMessageHeaderAccessor) throws Exception {
        UserToken user = webSocketManagerService.getUser(simpMessageHeaderAccessor).orElseThrow(() -> new BusinessException(MsgCode.ERROR_AUTH));
        Message msg = Message.builder().userId(user.getId()).contents(message.getContents()).build();
        msg.addUserMessage(UserMessage.builder().userId(toUserId).confirm(false).build());
        msg.addUserMessage(UserMessage.builder().userId(user.getId()).confirm(false).build());
        msg = messageRepository.save(msg);
        webSocketManagerService.sendToUserByUserId("/queue"+URI_PREFIX, Arrays.asList(msg), user.getId(), toUserId);

    }

    @MessageMapping(URI_PREFIX+"/{userId}")
    @SendToUser("/queue"+URI_PREFIX)
    @JsonView({JsonViewApi.class})
    public List<Message> getMessages(@DestinationVariable("userId") String fromUserId, Principal principal, SimpMessageHeaderAccessor simpMessageHeaderAccessor) throws Exception {
        UserToken user = webSocketManagerService.getUser(simpMessageHeaderAccessor).orElseThrow(() -> new BusinessException(MsgCode.ERROR_AUTH));
        List<Message> data = this.userMessageRepository.findByUserIdAndMessageUserId(user.getId(), fromUserId);
        return data;
//        return roomMessages.stream().map(it -> it.getMessage()).collect(Collectors.toList());
    }


//    @MessageMapping(URI_PREFIX+"/send-room-messages")
//    public void createRoom(RequestSendRoomMessage message, Principal principal, SimpMessageHeaderAccessor simpMessageHeaderAccessor) throws Exception {
//        Optional<UserToken> userToken = webSocketManagerService.getUser(simpMessageHeaderAccessor);
//        UserToken user = userToken.orElseThrow(() -> new BusinessException(MsgCode.ERROR_AUTH));
//        Message msg = Message.builder().userId(user.getId()).contents(message.getContents()).build();
//        userRoomRepository.findAllByRoom(message.getRoomId()).stream().forEach(it -> {
//            msg.addRoomMessage(RoomMessage.builder().roomId(message.getRoomId()).userId(it.getUserId()).build());
//        });
//        messageRepository.save(msg);
//    }

}
