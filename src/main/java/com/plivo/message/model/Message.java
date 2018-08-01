package com.plivo.message.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Message {

	
	private String from;
	private String to;
	private String text;
	
	public Message() {
			}
	
	
}
