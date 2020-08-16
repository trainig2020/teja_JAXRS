package com.control;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.model.Message;
import com.service.MessageService;

@Path("/message")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {

	private MessageService messageService = new MessageService();

	@GET
	public List<Message> getAllMessage() {
		List<Message> msg = messageService.getAllMessages();
		return msg;
	}

	@POST
	public Message addMessage(Message message) {
		message.setId(message.getId());
		message.setMessage(message.getMessage());
		message.setAuthor(message.getAuthor());
		message.setCreated(message.getCreated());
		return messageService.addMessage(message);
	}

	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long messageId) {
		Message message = messageService.getMessages(messageId);
		return message;
	}

	@PUT
	@Path("/{messageId}")
	public Message putMessage(@PathParam("messageId") long messageId, Message message) {
		message.setId(messageId);
		return messageService.updateMessage(message);
	}

	@DELETE

	@Path("/{messageId}")

	@Produces(MediaType.APPLICATION_JSON)
	public void deleteMessage(@PathParam("messageId") long messageId) {
		messageService.removeMessage(messageId);
	}
	
	@Path("{messageId}/comment")
	public CommentResource getComment() {
		return new CommentResource();
	}

}
