package com.clone.chat.service;

import com.clone.chat.domain.Room;
import com.clone.chat.domain.UserRoom;
import com.clone.chat.repository.RoomRepository;
import com.clone.chat.repository.UserRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
@Slf4j
public class RoomService {

    @Autowired
    UserRoomRepository userRoomRepository;

    @Autowired
    RoomRepository roomRepository;

    public List<Room> userRoomFindAllByUserId(String id) {
        List<Room> allByUserRoom_userId = roomRepository.findAllByUserRoom_UserId(id);
        return allByUserRoom_userId;
    }
}
