package com.advancia;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.advancia.daos.config.HibernateSessionFactory;
import com.advancia.models.Allergen;

public class MainTester {

	public static void main(String[] args) {
		SessionFactory sessionFactory = null;
		try {
			sessionFactory = HibernateSessionFactory.getSessionFactory();
			Session session = sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();

			Allergen allergen = session.get(Allergen.class, 1);
			
			System.out.println(allergen);
			
			System.out.println("Before committing transaction");
			tx.commit();
			sessionFactory.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionFactory != null && !sessionFactory.isClosed())
				sessionFactory.close();
		}

	}

}
