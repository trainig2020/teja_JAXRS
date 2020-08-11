package org.PathParamJerseyservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.PathParamJersey.model.Message;
import org.PathParamJerseyDatabase.DatabaseClass;


public class MessageService {
	public  Map<Long, Message> messages=DatabaseClass. getMessages();
	public MessageService() {
		messages.put(1l,new Message(1,"helloworld","teja")); 
		messages.put(2l,new Message(2,"helloworld","teja")); 
		
	}
	
	public List<Message> getAllMessages(){
		/*
		 * Message m1=new Message(1l,"helloworld","teja"); Message m2=new
		 * Message(2l,"hellojersey","teja"); List<Message> list=new ArrayList<>();
		 * list.add(m1); list.add(m2); return list;
		 */
    return new ArrayList<Message>(messages.values());
	}

	public Message getMessage(long id) {
		return messages.get(id);
		
	}
	
	public Message addMessage(Message message) {
		message.setId(messages.size()+1);
		messages.put(message.getId(), message);
		return message;
		}
	public Message updateMessage(Message message) {
		if(message.getId()<=0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
		}
	public Message removeMessage(Long id) {
		return messages.remove(id);
	}
	
}
