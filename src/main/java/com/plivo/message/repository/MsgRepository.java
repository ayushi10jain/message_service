package com.plivo.message.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.plivo.message.dto.MessageDTO;
import com.plivo.message.model.Message;


public interface MsgRepository extends CrudRepository<MessageDTO, Long>{
 // MessageDTO findbyid(Long messageId);
	
}
