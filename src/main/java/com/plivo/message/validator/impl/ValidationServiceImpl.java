package com.plivo.message.validator.impl;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.plivo.message.exception.BaseHttpException;
import com.plivo.message.model.Message;
import com.plivo.message.validator.service.ValidationService;

@Service
public class ValidationServiceImpl implements ValidationService {
	
   @Override
	public @Valid void validateInbound(Message msg) throws BaseHttpException {
		if(msg.getFrom().length()==0 || msg.getFrom()==null || msg.getFrom().isEmpty()) {
			throw new BaseHttpException("FROM cannot be null","FROM is missing");
		}
		if(msg.getTo().length()==0 || msg.getTo()==null || msg.getTo().isEmpty()) {
			throw new BaseHttpException("To cannot be null","To is missing");
		}
		if(msg.getText().length()==0 || msg.getText()==null || msg.getText().isEmpty()) {
			throw new BaseHttpException("TEXT cannot be null","TEXT is missing");
		}
		if(msg.getFrom().length()<6 || msg.getFrom().length()>16) {
			throw new BaseHttpException("length is in between 6-16","FROM is invalid");
		}
		if(msg.getTo().length()<6 || msg.getTo().length()>16) {
			throw new BaseHttpException("length is in between 6-16","TO is invalid");
		}
		if(!msg.getTo().matches("\\d+") || !msg.getFrom().matches("\\d+")) {
			throw new BaseHttpException("to and from must be digits only","invalid FROM OR TO");
		}
		

	}

   @Override
	public void validateOutbound(String from, String to, String text) throws BaseHttpException {
		if(from.length()==0 || from==null || from.isEmpty()) {
			throw new BaseHttpException("FROM cannot be null","FROM is missing");
		}
		if(to.length()==0 || to==null || to.isEmpty()) {
			throw new BaseHttpException("To cannot be null","To is missing");
		}
		if(text.length()==0 || text==null || text.isEmpty()) {
			throw new BaseHttpException("TEXT cannot be null","TEXT is missing");
		}
		if(from.length()<6 || from.length()>16) {
			throw new BaseHttpException("length is in between 6-16","FROM is invalid");
		}
		if(from.length()<6 || from.length()>16) {
			throw new BaseHttpException("length is in between 6-16","TO is invalid");
		}
		if(!from.matches("\\d+") || !to.matches("\\d+")) {
			throw new BaseHttpException("to and from must be digits only","invalid FROM OR TO");
		}
		
	}
}
