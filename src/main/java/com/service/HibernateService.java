package com.service;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Created by pc8 on 03.11.15.
 */
public class HibernateService {
    public HibernateService(){}

    private static SessionFactory sessionFactory;

    public static SessionFactory createSessionFactory(){
      sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
      return sessionFactory;
    }
}
