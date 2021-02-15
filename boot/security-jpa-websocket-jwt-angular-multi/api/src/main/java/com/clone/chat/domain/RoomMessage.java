package com.clone.chat.domain;

import com.clone.chat.model.ModelBase;
import com.clone.chat.model.view.json.JsonViewApi;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "ROOM_MESSAGE")
public class RoomMessage extends ModelBase {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@JsonView({JsonViewApi.class})
	Long id;

	@Column(name = "ROOM_ID")
	@JsonView({JsonViewApi.class})
	Long roomId;

	@Column(name = "USER_ID")
	@JsonView({JsonViewApi.class})
	String userId;

	@Column(name = "CONFIRM")
	@JsonView({JsonViewApi.class})
	Boolean confirm;

	@ManyToOne
	@JoinColumn(name = "MESSAGE_ID") //, referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
	@JsonBackReference
	@JsonView({JsonViewApi.class})
	private Message message;

	@Builder
	public RoomMessage(Long id, Long roomId, String userId, Boolean confirm, Message message) {
		this.id = id;
		this.roomId = roomId;
		this.userId = userId;
		this.confirm = confirm;
		this.message = message;
	}
}
