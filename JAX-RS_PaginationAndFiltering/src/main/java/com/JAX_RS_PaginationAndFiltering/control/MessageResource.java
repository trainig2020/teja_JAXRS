package com.JAX_RS_PaginationAndFiltering.control;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.JAX_RS_PaginationAndFiltering.control.beans.MessageFilterBean;
import com.JAX_RS_PaginationAndFiltering.model.Message;
import com.JAX_RS_PaginationAndFiltering.service.MessageService;

@Path("/message")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	MessageService messageService = new MessageService();
	
	@GET
	
	public List<Message> getJSONMessage(@BeanParam MessageFilterBean filterBean){
	
		if(filterBean.getYear()>0) {
			return messageService.getMessagesForYear(filterBean.getYear());
		}
		if(filterBean.getStart()>=0 &&filterBean.getSize()>=0) {
			return messageService.getMessagePaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessage();
	}
	/*
	 * @GET
	 * 
	 * @Produces(MediaType.TEXT_XML) public List<Message> getXMLMessage(@BeanParam
	 * MessageFilterBean filterBean){ System.out.println("XML method called");
	 * if(filterBean.getYear()>0) { return
	 * messageService.getMessagesForYear(filterBean.getYear()); }
	 * if(filterBean.getStart()>=0 &&filterBean.getSize()>=0) { return
	 * messageService.getMessagePaginated(filterBean.getStart(),
	 * filterBean.getSize()); } return messageService.getAllMessage(); }
	 */
	
	@GET
	@Path("/{messageId}")

	public Message getMessage(@PathParam("messageId") long messageId,@Context UriInfo uriInfo) {
		Message message= messageService.getMessages(messageId);
		
		message.addLink(getUriForSelf(uriInfo, message), "self");
		message.addLink(getUriForProfile(uriInfo, message), "profile");
		message.addLink(getUriForComment(uriInfo, message), "comment");
		return message;
	}

	private String getUriForComment(UriInfo uriInfo, Message message) {
		URI uri=uriInfo.getBaseUriBuilder().path(CommentResource.class)
				.path(MessageResource.class)
				.path(MessageResource.class,"getCommentResource")
				.path(CommentResource.class)
				.resolveTemplate("messageId", message.getId())
				.build();
		return uri.toString();
		
	}

	private String getUriForProfile(UriInfo uriInfo, Message message) {
	URI uri=uriInfo.getBaseUriBuilder().path(ProfileResource.class)
			.path(message.getAuthor())
			.build();
	return uri.toString();
	}

	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri=uriInfo.getBaseUriBuilder()
		.path(MessageResource.class).path(Long.toString(message.getId())).build().toString();
		return uri;
	}
	@POST

	
	public Response postMessage(Message message,@Context UriInfo uriInfo)  {
		System.out.println(uriInfo.getAbsolutePath());
	Message newMessage=	 messageService.addMessage(message);
	String  newId=String.valueOf(newMessage.getId());
	URI uri=uriInfo.getAbsolutePathBuilder().path(newId).build();
	return	Response.created(uri).entity(newMessage)
			.build();
		//return messageService.addMessage(message);
	}
	
	@PUT
	@Path("/{messageId}")
	
	public Message putMessage(@PathParam("messageId") long messageId,Message message) {
		message.setId(messageId);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	
	public void deleteMessage(@PathParam("messageId") long messageId) {
		 messageService.removeMessage(messageId);
	}
	
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}

}
