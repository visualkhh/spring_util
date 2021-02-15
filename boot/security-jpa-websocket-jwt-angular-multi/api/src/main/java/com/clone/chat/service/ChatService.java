package com.clone.chat.service;

import java.util.List;

import com.clone.chat.domain.UserRoom;
import com.clone.chat.repository.UserRoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clone.chat.model.ChatRoomDto;
import com.clone.chat.repository.ChatRoomRepository;
import com.clone.chat.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ChatService {

    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRoomRepository userInChatRoomRepository;

    @Transactional
    public Long chatRoomCreate(ChatRoomDto dto) {
//        ChatRoom chatRoom = chatRoomRepository.save(dto.toEntity());
//        //연결테이블 처리
//        Optional<User> user = userRepository.findById(dto.getUserId());
//        user.orElseThrow(() -> new NoSuchElementException());
//        UserInChatRoom userInChatRoom = new UserInChatRoom(chatRoom, user.get(), true);
//        userInChatRoomRepository.save(userInChatRoom);
//        return chatRoom.getId();
        return null;
    }

    public List<UserRoom> getList(String userId, String search) {
        return null;
        //return userInChatRoomRepository.findByUser(userId, search);
    }

    @Transactional
    public void invite(List<String> users, Long chatRoomId) {
//        ChatRoom chatRoom = chatRoomRepository.getOne(chatRoomId);
//
//        for (String userId : users) {
//            User user = userRepository.getOne(userId);
//            UserInChatRoom userInChatRoom = new UserInChatRoom(chatRoom, user, true);
//            userInChatRoomRepository.save(userInChatRoom);
//        }
    }
}
