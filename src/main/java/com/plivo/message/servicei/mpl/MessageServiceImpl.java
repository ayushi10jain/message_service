package com.plivo.message.servicei.mpl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

import com.plivo.message.dto.MessageDTO;
import com.plivo.message.exception.BaseHttpException;
import com.plivo.message.model.Message;
import com.plivo.message.model.Response;
import com.plivo.message.repository.MsgRepository;
import com.plivo.message.service.MessageService;
import com.plivo.message.validator.service.ValidationService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

@Service
public class MessageServiceImpl implements MessageService {

	private static final Logger LOGGER = Logger.getLogger(MessageServiceImpl.class);
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

	@Autowired
	MsgRepository msgrepo;

	@Autowired
	private RedisTemplate<String, String> template;

	
	MessageDTO msgdto = new MessageDTO();

	@Override
	public Response inboundSms(Message msg) throws BaseHttpException {
		LOGGER.info("here::::::::;");
		String trimmedMesssage = msg.getText().trim();
		if (trimmedMesssage.contains("STOP")) {
			LOGGER.info("here::::::::;");
			saveDataToCache(msg);
		}
		saveDataToDB(msg);

		return new Response("inbound sms is ok", "");
	}

	public void saveDataToCache(Message msg) {

		LOGGER.info("here::::::::####");
		template.opsForValue().set(msg.getFrom() + "-" + msg.getTo(), "dummy");

		template.expire(msg.getFrom() + "-" + msg.getTo(), 4, TimeUnit.HOURS);
	}

	public void saveDataToDB(Message msg) {
		LOGGER.info("here::::::::;1111");
		LOGGER.info("here::::::::;1111" + msg.getText());
		msgdto.setCount(1L);
		msgdto.setFrom(msg.getFrom());
		msgdto.setTo(msg.getTo());
		msgdto.setText(msg.getText());
		msgdto.setText(msg.getText());
		msgdto.setTimestamp(new Date(System.currentTimeMillis()));

		msgrepo.save(msgdto);

	}

	@Override
	public Response outboundSms(String from, String to, String text) throws BaseHttpException {

		if (template.hasKey(from + "-" + to)) {
			System.out.println("m here");
			throw new BaseHttpException("Request forbidden", "from and to are there in cache");
		}

		setRateLimit(from);

		return new Response("valid input", "sms sent");

	}

	public void setRateLimit(String from) throws BaseHttpException {
		
		if (template.hasKey(from) && template.opsForValue().get(from) != null) {
			String sub = (template.opsForValue().get(from));
			sub = sub.substring(sub.indexOf(":")+1, sub.length());
			if (Integer.valueOf(sub) >= 50) {
				throw new BaseHttpException("too may request from the same source", "User blocked");
			} else {
				String time_stamp = template.opsForValue().get(from);
				Long old_timestamp = Long.valueOf(time_stamp.substring(0, time_stamp.indexOf(":")));
				Long current_timestamp = new Timestamp(System.currentTimeMillis()).getTime();
				Long diff = (old_timestamp - current_timestamp) / (60 * 60 * 1000);

				if (diff <= 0) {
					int count = Integer.valueOf(sub) + 1;
					template.opsForValue().set(from, old_timestamp.toString() + ":" + String.valueOf(count));
						
				} else {
					template.opsForValue().set(from, current_timestamp.toString() + ":" + String.valueOf(1));
				}
			}

		}else {
			 Long timestamp=new Timestamp(System.currentTimeMillis()).getTime();
			 String value=timestamp.toString();
			 int counter=1;
			 String count= String.valueOf(counter);
			 template.opsForValue().set(from,value+":"+count);
			 template.expire(from, 5, TimeUnit.MINUTES);
		}
	}
}
