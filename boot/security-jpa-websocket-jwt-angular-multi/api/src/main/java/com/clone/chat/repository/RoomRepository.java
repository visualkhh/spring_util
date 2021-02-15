package com.clone.chat.repository;

import com.clone.chat.domain.Message;
import com.clone.chat.domain.Room;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("" +
            "select " +
            " distinct a " +
            "from Room a left join fetch a.userRooms b " +
            "where" +
            " a.id in (select b.room from a.userRooms b where b.userId = :userId) " +
            "order by a.lastMsgDt desc " +
            "")
    List<Room> findAllByUserRoom_UserId(String userId);


    @EntityGraph(value = "Room.userRooms", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Room> findById(Long id);
}
