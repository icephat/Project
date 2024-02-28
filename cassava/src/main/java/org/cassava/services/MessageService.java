package org.cassava.services;

import java.util.List;

import org.cassava.model.Message;
import org.cassava.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("messageService")
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;
	
	public List<Message> findAll() {
		return (List<Message>) messageRepository.findAll();
	}

	public Message findById(int id) {
		Message user = messageRepository.findById(id).orElse(null);
		return user;
	}

	public Message save(Message k) {
		return messageRepository.save(k);
	}

	public void deleteById(int id) {
		messageRepository.deleteById(id);
	}
}
