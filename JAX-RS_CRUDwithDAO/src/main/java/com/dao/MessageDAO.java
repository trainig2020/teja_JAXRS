package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.model.Message;
import com.util.HibernateUtil;

public class MessageDAO {

	@SuppressWarnings("unchecked")
	public List<Message> getAllMessage() {

		Session session = HibernateUtil.getSession();
		session.beginTransaction();

		List<Message> lstOfMsg = session.createQuery("from Message").list();

		System.out.println("All details of Message displayed");

		return lstOfMsg;

	}

	public Message insertMessage(Message msg) {

		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSession();

			transaction = session.beginTransaction();
			session.save(msg);
			System.out.println("Datas Inserted");
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return msg;

	}
	
	public Message updateMessage(Message msg) {

		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSession();

			transaction = session.beginTransaction();
			session.update(msg);
			System.out.println("Datas Updated");
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return msg;

	}
	
	
	public void deleteMessage(long id) {

		Transaction transaction = null;
		Message msg = null;

		try {
			Session session = HibernateUtil.getSession();

			transaction = session.beginTransaction();
			msg = (Message) session.get(Message.class, id);
			session.delete(msg);
			System.out.println("Datas Deleted");
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		

	}
	
	
	public Message getMessageById(long id) {

		Transaction transaction = null;
		Message msg = null;
		try {
			Session session = HibernateUtil.getSession();

			transaction = session.beginTransaction();
			msg = (Message) session.get(Message.class, id);
			System.out.println("Datas Deleted");
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return msg;
		

	}
	
	
	

}
