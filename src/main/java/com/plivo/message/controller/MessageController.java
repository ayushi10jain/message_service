package com.plivo.message.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.plivo.message.exception.BaseHttpException;
import com.plivo.message.model.Message;
import com.plivo.message.model.Response;
import com.plivo.message.service.MessageService;
import com.plivo.message.servicei.mpl.MessageServiceImpl;
import com.plivo.message.validator.service.ValidationService;

@RestController
@RequestMapping("/message")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private ValidationService validationService; 
	
	
	@PostMapping("/inbound/sms")
	public @Valid Response inboundMsg(@RequestBody @Valid Message msg) throws BaseHttpException {
		validationService.validateInbound(msg);
		Response response=messageService.inboundSms(msg);
		return response;
		//return msgimpl.addToFrom(msg);
		
	}
	
	@PostMapping("/outbound/sms")
	public Response outboundMsg(@RequestParam String from,@RequestParam String to, @RequestParam String text) throws BaseHttpException {
		validationService.validateOutbound(from, to, text);
		Response response=messageService.outboundSms(from, to, text);
		return response;
}
}
