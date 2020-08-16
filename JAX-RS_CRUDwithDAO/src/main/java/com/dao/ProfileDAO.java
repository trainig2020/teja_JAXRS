package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.model.Profile;
import com.util.HibernateUtil;

public class ProfileDAO {
	
	@SuppressWarnings("unchecked")
	public List<Profile> getAllProfile() {

		Session session = HibernateUtil.getSession();
		session.beginTransaction();

		List<Profile> lstOfProfile = session.createQuery("from Profile").list();

		System.out.println("All details of Profile displayed");

		return lstOfProfile;

	}
	
	public Profile insertProfile(Profile prof) {

		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSession();

			transaction = session.beginTransaction();
			session.save(prof);
			System.out.println("Datas Inserted");
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return prof;

	}
	
	public Profile updateProfile(Profile prof) {

		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSession();

			transaction = session.beginTransaction();
			session.update(prof);
			System.out.println("Datas Updated");
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return prof;

	}
	
	
	public void deleteProfile(String profileName) {

		Transaction transaction = null;
		Profile prof = null;

		try {
			Session session = HibernateUtil.getSession();

			transaction = session.beginTransaction();
			prof = (Profile) session.get(Profile.class, profileName);
			session.delete(prof);
			System.out.println("Datas Deleted");
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		

	}
	
	
	public Profile getProfile(String profileName) {

		Transaction transaction = null;
		Profile prof = null;
		try {
			Session session = HibernateUtil.getSession();

			transaction = session.beginTransaction();
			prof = (Profile) session.get(Profile.class, profileName);
			System.out.println("Datas Deleted");
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return prof;
		

	}

}
