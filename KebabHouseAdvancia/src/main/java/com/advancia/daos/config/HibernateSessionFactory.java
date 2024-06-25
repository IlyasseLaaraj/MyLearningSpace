package com.advancia.daos.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateSessionFactory {

	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) {
			try {
				registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
				Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
				sessionFactory = metadata.getSessionFactoryBuilder().build();
			}catch(Exception e) {
				e.printStackTrace();
				if(registry!=null) {
					StandardServiceRegistryBuilder.destroy(registry);
				}
			}
		}
	return sessionFactory;
	}
}
