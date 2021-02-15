package com.clone.chat.controller.ws.rooms.model;

import com.clone.chat.domain.UserRoom;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class RequestCreateRoom {
    String name;
    List<String> users;
//    String targetUser;

    public void addUser(String user){
        this.users = Optional.ofNullable(this.users).orElseGet(() -> new ArrayList<>());
        this.users.add(user);
    }
}
