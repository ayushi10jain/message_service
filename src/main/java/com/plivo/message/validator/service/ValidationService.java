package com.plivo.message.validator.service;

import com.plivo.message.exception.BaseHttpException;
import com.plivo.message.model.Message;

public interface ValidationService {
	
	public void validateInbound(Message msg) throws BaseHttpException;
	
	public void validateOutbound(String from, String to, String text) throws BaseHttpException;

}
