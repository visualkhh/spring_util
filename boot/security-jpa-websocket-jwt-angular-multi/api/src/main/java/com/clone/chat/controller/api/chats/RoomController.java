package com.clone.chat.controller.api.chats;

import com.clone.chat.controller.api.ApiController;
import com.clone.chat.domain.Room;
import com.clone.chat.domain.UserRoom;
import com.clone.chat.repository.UserRoomRepository;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(RoomController.URI_PREFIX)
@Slf4j
@Api(tags = "방")
public class RoomController {
    public static final String URI_PREFIX = ApiController.URI_PREFIX + "/rooms";

    @Autowired
    UserRoomRepository userRoomRepository;

    @GetMapping("/{roomId}")
    public List<UserRoom> rooms(@PathVariable("roomId") Long roomId) {
//       return userRoomRepository.findAllByRoom(Room.builder().id(roomId).build());
       return userRoomRepository.findAllByRoom(roomId);
    }


}
