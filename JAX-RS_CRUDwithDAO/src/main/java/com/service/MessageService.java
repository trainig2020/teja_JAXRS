package com.service;

import java.util.List;



import com.dao.MessageDAO;
import com.model.Message;

public class MessageService {

	private MessageDAO messageDAO = new MessageDAO();

	public List<Message> getAllMessages() {

		return messageDAO.getAllMessage();

	}

	public Message getMessages(long id) {
		return messageDAO.getMessageById(id);
	}

	public Message addMessage(Message message) {
		return messageDAO.insertMessage(message);
	}

	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}
		return messageDAO.updateMessage(message);
	}

	public void removeMessage(long id) {
		 messageDAO.deleteMessage(id);
	}

}
