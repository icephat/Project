package org.cassava.services;

import java.util.ArrayList;
import java.util.List;

import org.cassava.model.Message;
import org.cassava.model.Messagereceiver;
import org.cassava.model.User;
import org.cassava.model.dto.MessageAndReceiverDTO;
import org.cassava.repository.MessagereceiverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessagereceiverService {

	@Autowired
	private MessagereceiverRepository messagereceiverRepository;
	
	@Autowired
	private UserService userService;
	
	public List<Messagereceiver> findAll(){
		return (List<Messagereceiver>) messagereceiverRepository.findAll();
	}

	public Messagereceiver findById(int id) {
		return messagereceiverRepository.findById(id).get();
	}
	
	public Messagereceiver findByUserIdAndMessageId(int userId,int messageId) {
		return messagereceiverRepository.findByUserIdAndMessageId(userId,messageId);
	}
	
	public Messagereceiver save(Messagereceiver messagereceiver) {
		return messagereceiverRepository.save(messagereceiver);
	}

	public void delete(Messagereceiver messagereceiver) {
		messagereceiverRepository.delete(messagereceiver);
	}
	
	public List<Messagereceiver> findByUserId(int id) {
		return messagereceiverRepository.findByUserId(id);
	}
	
	public List<Messagereceiver> findByUserIdAndReadStatus(int id,String status) {
		return messagereceiverRepository.findByUserIdAndReadStatus(id,status);
	}
	
	public List<MessageAndReceiverDTO> findMessageAndReceiverDTOByUserId(String username) {
		User user = userService.findByUsername(username);
		List<Messagereceiver> messagereceivers = messagereceiverRepository.findMessageAndReceiverDTOByUserId(user);
		List<MessageAndReceiverDTO> messageAndReceiverDTOs = new ArrayList<MessageAndReceiverDTO>();
		for(Messagereceiver messagereceiver : messagereceivers) {
			MessageAndReceiverDTO messageAndReceiverDTO = new MessageAndReceiverDTO();
			messageAndReceiverDTO.setMessageId(messagereceiver.getMessage().getMessageId());
			messageAndReceiverDTO.setSendDate(messagereceiver.getMessage().getSendDate());
			messageAndReceiverDTO.setTitle(messagereceiver.getMessage().getTitle());
			messageAndReceiverDTO.setText(messagereceiver.getMessage().getText());
			messageAndReceiverDTO.setType(messagereceiver.getMessage().getType());
		
			messageAndReceiverDTO.setReadStatus(messagereceiver.getReadStatus());
			
			messageAndReceiverDTO.setReceiver(messagereceiver.getUser().getUsername());
			messageAndReceiverDTO.setSender(messagereceiver.getMessage().getUser().getUsername());
			messageAndReceiverDTOs.add(messageAndReceiverDTO);
		}
		return messageAndReceiverDTOs;	
	}
}
