package com.clone.chat.repository;

import com.clone.chat.domain.Room;
import com.clone.chat.domain.RoomMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface RoomMessageRepository extends JpaRepository<RoomMessage, Long> {
    @Query("" +
            "select a from RoomMessage a join a.message b where b.id = :messageId" +
            "")
    List<RoomMessage> findAllByMessageId(@Param("messageId") Long messageId);

    List<RoomMessage> findAllByRoomIdAndUserId(Long roomId, String userId);


    @Modifying
    @Query("update RoomMessage a set a.confirm = :confirm where a.userId = :userId and a.message.id = :messageId" +
            "")
    int updateChecktByMessageIdAndUserId(@Param("confirm") Boolean confirm, @Param("messageId") Long messageId, @Param("userId") String userId);
}
