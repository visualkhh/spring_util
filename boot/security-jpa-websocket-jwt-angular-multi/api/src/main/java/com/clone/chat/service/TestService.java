package com.clone.chat.service;

import com.clone.chat.model.ChatRoomDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class TestService {


    @SendTo("/topic/brodcast/{roomNo}")
    public ChatRoomDto chat(Date date) {
        return new ChatRoomDto("a", date.toString());
    }


}
