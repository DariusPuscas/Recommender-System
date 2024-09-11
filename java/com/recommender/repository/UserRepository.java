package com.recommender.repository;

import com.recommender.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UserRepository {
    final private SessionFactory factory;

    public UserRepository() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    public User findById(int userId) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            User user = session.get(User.class, userId);
            session.getTransaction().commit();
            return user;
        } finally {
            session.close();
        }
    }
}