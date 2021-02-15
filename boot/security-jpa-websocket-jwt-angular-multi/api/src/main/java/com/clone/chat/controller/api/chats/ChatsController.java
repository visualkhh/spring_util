package com.clone.chat.controller.api.chats;

import com.clone.chat.controller.api.ApiController;
import com.clone.chat.domain.UserRoom;
import com.clone.chat.model.ChatRoomDto;
import com.clone.chat.model.ResponseForm;
import com.clone.chat.service.ChatService;
import com.clone.chat.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(ChatsController.URI_PREFIX)
@Slf4j
@Api(tags = "체팅")
public class ChatsController {
    public static final String URI_PREFIX = ApiController.URI_PREFIX + "/chats";

    @Autowired
    TestService testService;

    @Autowired
    ChatService chatService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @ApiOperation(value = "방만들기")
    @PostMapping("/rooms")
    public ResponseForm roomCreate(@RequestBody ChatRoomDto dto) {
        Long roomId = chatService.chatRoomCreate(dto);

        return new ResponseForm("roomId", roomId);
    }

    @GetMapping("/rooms")
    public List<UserRoom> roomList(String userId, String search) {
        return chatService.getList(userId, search);
    }

//    //방입장시 알림메시지
//    @MessageMapping("/joins/{roomNo}")
//    @SendTo("/topic/greetings/{roomNo}")
//    public Greeting greeting(ChatMessage message, SimpMessageHeaderAccessor accessor) throws Exception {
//        Thread.sleep(100); // delay
////        messagingTemplate.
////        accessor.getUser()
//        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
//    }
//
//    //채팅 송신
//    @MessageMapping("/chats/{roomNo}")
//    @SendTo("/topic/chats/{roomNo}")
//    public ChatRoomDto chat(ChatRoomDto chat) throws Exception {
//        return new ChatRoomDto(chat.getName(), chat.getMessage());
//    }


}
