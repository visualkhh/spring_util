package com.clone.chat.model;

import com.clone.chat.domain.Room;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatRoomDto extends ModelBase {

    @ApiModelProperty(notes = "방이름")
    String chatRoomName;
    @ApiModelProperty(notes = "사용자이이디")
    String userId;

//    public Room toEntity() {
//        return Room.builder()
//                .name(chatRoomName)
//                .admin(userId)
//                .build();
//    }


    private String name;
    private String message;

    public ChatRoomDto(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}
