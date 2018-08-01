package com.example.message;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.plivo.message.exception.BaseHttpException;
import com.plivo.message.model.Message;
import com.plivo.message.model.Response;
import com.plivo.message.service.MessageService;
import com.plivo.message.servicei.mpl.MessageServiceImpl;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class MessageServiceImplTest {
	
	
	MessageServiceImpl messageService = new MessageServiceImpl() ;
	
	 @Test
	public void testInboundService() throws BaseHttpException {
//	Response resp=	 messageService.inboundSms(new Message("6758903456","367834949","hi"));
//	Assert.assertEquals("inbound sms is ok", resp.getMessage());
	 }
	 
	 @Test
	public void testOutboundService() throws BaseHttpException {
//		 Response resp=messageService.outboundSms("6758903456","367834949","hi");
//		 
//		 Assert.assertEquals("outbound sms is ok", resp.getMessage());
	 }
}
