package com.clone.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clone.chat.domain.Room;

public interface ChatRoomRepository extends JpaRepository<Room, Long>{

}
