package com.plivo.message.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="message")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		
		@Column(name="sms_from")
		private String from;
		 
		@Column(name="sms_to")
		private String to;
		
		@Column(name="time_stamp")
		private Date timestamp;
		
		@Column(name="text") 
		private String text;
		
		@Column(name="request_count")
		private Long count;
		
}


