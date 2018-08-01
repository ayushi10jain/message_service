package com.plivo.message.service;

import com.plivo.message.exception.BaseHttpException;
import com.plivo.message.model.Message;
import com.plivo.message.model.Response;

public interface MessageService {

	public Response inboundSms(Message msg) throws BaseHttpException;
	
	public Response outboundSms(String from,String to, String text)  throws BaseHttpException;
		
	
}
