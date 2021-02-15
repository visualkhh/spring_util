package com.clone.chat.domain;

import javax.persistence.*;

import com.clone.chat.model.ModelBase;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "USER_MESSAGE")
public class UserMessage extends ModelBase {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	Long id;

	@Column(name = "USER_ID")
	String userId;

	@Column(name = "CONFIRM")
	Boolean confirm;

	@ManyToOne
	@JoinColumn(name = "MESSAGE_ID")
	@JsonBackReference
	private Message message;



	@Builder
	public UserMessage(Long id, String userId, Boolean confirm, Message message) {
		this.id = id;
		this.userId = userId;
		this.confirm = confirm;
		this.message = message;
	}
}
