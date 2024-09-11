package com.recommender.repository;

import com.recommender.model.Rating;
import com.recommender.model.User;
import com.recommender.model.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class RatingRepository {

    final private SessionFactory factory;

    public RatingRepository() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    // Saves a rating
    public void saveRating(Rating rating) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            session.save(rating);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();/// modified, might crash
        }
        finally {
            session.close();
        }
    }

    // Return ALL ratings
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

    // finds the ratings for a given user
    public List<Rating> findByUser(User targetUser) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            List<Rating> ratings = session.createQuery("from Rating r where r.user = :user", Rating.class)
                    .setParameter("user", targetUser)
                    .getResultList();
            session.getTransaction().commit();
            return ratings;
        } finally {
            session.close();
        }
    }
    // finds the most similar users (that rated the same items)
    public List<User> findSimilarUsers(User targetUser) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();

            // Selectează utilizatori care au evaluat aceleași articole ca și targetUser
            List<User> similarUsers = session.createQuery(
                            "select distinct r.user from Rating r " +
                                    "where r.user != :user and r.item in " +
                                    "(select r2.item from Rating r2 where r2.user = :user)", User.class)
                    .setParameter("user", targetUser)
                    .getResultList();

            session.getTransaction().commit();
            return similarUsers;
        } finally {
            session.close();
        }
    }
}
