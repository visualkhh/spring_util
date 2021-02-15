package com.clone.chat.domain;

import javax.persistence.*;

import com.clone.chat.model.ModelBase;
import com.clone.chat.model.view.json.JsonViewApi;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

@Getter  @Setter
@NoArgsConstructor
@Entity
@Table(name = "USER_ROOM")
public class UserRoom extends ModelBase {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	@JsonView({JsonViewApi.class})
	Long id;

//	@Column(name="ROOM_ID")
//	Long roomId;

	@Column(name="USER_ID")
	@JsonView({JsonViewApi.class})
	String userId;

//	@JoinColumns(@JoinColumn(name = "ROOM_IDS"))
//	@JoinTable(name = "ROOM",)
	@ManyToOne
	@JoinColumn(name = "ROOM_ID") //, nullable = false, insertable = false, updatable = false
	@JsonBackReference
	@JsonView({JsonViewApi.class})
	private Room room;

	@ManyToOne
	@JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
	@JsonView({JsonViewApi.class})
	private User user;

	@Builder
	public UserRoom(Long id, Long roomId, String userId, Room room, User user) {
		this.id = id;
//		this.roomId = roomId;
		this.userId = userId;
		this.room = room;
		this.user = user;
	}

}
	
