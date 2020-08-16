
package com.service;

import java.util.List;

import com.dao.CommentDAO;
import com.model.Comment;

public class CommentService {
	private CommentDAO commentDAO = new CommentDAO();

	public List<Comment> getAllComment(long msgId) {

		return commentDAO.getAllComment(msgId);

	}

	public Comment getComment(long msgId, long id) {
		return commentDAO.getCommentById(msgId, id);
	}

	public Comment addComment(long msgId,Comment comment) {
		return commentDAO.insertComment(msgId, comment);
	}

	public Comment updateComment(long msgId,Comment comment) {
		if (comment.getId() <= 0) {
			return null;
		}
		return commentDAO.updateComment(msgId, comment);
	}

	public void removeComment(long msgId,long id) {
		 commentDAO.deleteComment(msgId, id);
	}


}
