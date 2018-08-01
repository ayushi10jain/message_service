package com.example.message;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.plivo.message.exception.BaseHttpException;
import com.plivo.message.model.Message;
import com.plivo.message.model.Response;
import com.plivo.message.service.MessageService;

public class MessageServiceImplTest {
	
	@Autowired 
	MessageService messageService;
	
	 @Test
	public void testInboundService() throws BaseHttpException {
	Response resp=	 messageService.inboundSms(new Message("6758903456","367834949","hi"));
	Assert.assertEquals("inbound sms is ok", resp.getMessage());
	 }
	 @Test
	public void testOutboundService() throws BaseHttpException {
		 Response resp=messageService.outboundSms("6758903456","367834949","hi");
		 Assert.assertEquals("outbound sms is ok", resp.getMessage());
	 }
}
