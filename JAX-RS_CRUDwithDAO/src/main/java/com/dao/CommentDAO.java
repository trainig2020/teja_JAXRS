package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.model.Comment;
import com.util.HibernateUtil;

public class CommentDAO {
	
	@SuppressWarnings("unchecked")
	public List<Comment> getAllComment(long msgId) {

		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		List<Comment> lst =  session.createQuery("from Comment where messageId=" + msgId).list();
		//List<Comment> lstOfCmt = session.createQuery("from Comment").list();

		System.out.println("All details of Comments displayed");

		return lst;

	}

	public Comment insertComment(long msgId, Comment cmt) {

		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSession();

			transaction = session.beginTransaction();
			List<Comment> lst =  session.createQuery("from Comment where messageId=" + msgId).list();
			session.save(cmt);
			System.out.println("Datas Inserted");
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return cmt;

	}
	
	public Comment updateComment(long msgId,Comment cmt) {

		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSession();

			transaction = session.beginTransaction();
			List<Comment> lst =  session.createQuery("from Comment where messageId=" + msgId).list();
			session.update(cmt);
			System.out.println("Datas Updated");
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return cmt;

	}
	
	
	public void deleteComment(long msgId,long id) {

		Transaction transaction = null;
		Comment cmt = null;

		try {
			Session session = HibernateUtil.getSession();

			transaction = session.beginTransaction();
			cmt = (Comment) session.get(Comment.class, id);
			List<Comment> lst =  session.createQuery("from Comment where messageId=" + msgId).list();
			session.delete(cmt);
			System.out.println("Datas Deleted");
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		

	}
	
	
	public Comment getCommentById(long msgId,long id) {

		Transaction transaction = null;
		Comment cmt = null;
		try {
			Session session = HibernateUtil.getSession();

			transaction = session.beginTransaction();
			cmt = (Comment) session.get(Comment.class, id);
			List<Comment> lst =  session.createQuery("from Comment where messageId=" + msgId).list();
			System.out.println("Datas Deleted");
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return cmt;
		

	}
	

}
