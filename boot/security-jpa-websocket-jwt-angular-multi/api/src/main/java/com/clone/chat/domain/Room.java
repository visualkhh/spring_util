package com.clone.chat.domain;

import javax.persistence.*;

import com.clone.chat.model.ModelBase;
import com.clone.chat.model.view.json.JsonViewApi;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ROOM")
@NamedEntityGraph(name = "Room.userRooms", attributeNodes = @NamedAttributeNode("userRooms"))
public class Room extends ModelBase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	@JsonView({JsonViewApi.class})
	private Long id;

	@Column(name = "NAME")
	@JsonView({JsonViewApi.class})
	private String name;
//	private Long msgCount;
//	private String admin;

	@Column(name = "LAST_MSG_DT")
	@JsonView({JsonViewApi.class})
	private ZonedDateTime lastMsgDt;

	@Column(name = "LAST_MSG_CONTENTS")
	@JsonView({JsonViewApi.class})
	private String lastMsgContents;

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL) //mappedBy = "room", , cascade = CascadeType.ALL
//	@JoinColumn(name = "ROOM_IDS", referencedColumnName = "ID", insertable = true, updatable = true)
	@JsonManagedReference
	@JsonView({JsonViewApi.class})
	List<UserRoom> userRooms;

	@OneToMany
	@JoinColumn(name = "ROOM_ID", referencedColumnName = "ID", insertable = true, updatable = true)
	@JsonManagedReference
	@JsonView({JsonViewApi.class})
	List<RoomMessage> roomMessages;

	@Builder
	public Room(Long id, String name, List<UserRoom> userRooms) {
		this.id = id;
		this.name = name;
		this.userRooms = userRooms;
	}



	public void addUserRoom(UserRoom userRoom){
		this.userRooms = Optional.ofNullable(this.userRooms).orElseGet(() -> new ArrayList<>());
		userRoom.setRoom(this);
		this.userRooms.add(userRoom);
	}
}
