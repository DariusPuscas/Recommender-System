package com.recommender.repository;

import com.recommender.model.Rating;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class RatingRepository {

    private SessionFactory factory;

    public RatingRepository() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    public void saveRating(Rating rating) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            session.save(rating);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    public List<Rating> getAllRatings() {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            List<Rating> ratings = session.createQuery("from Rating", Rating.class).getResultList();
            session.getTransaction().commit();
            return ratings;
        } finally {
            session.close();
        }
    }
}