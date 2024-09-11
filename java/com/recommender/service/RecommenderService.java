package com.recommender.service;

import com.recommender.model.Rating;
import com.recommender.repository.RatingRepository;
import com.recommender.model.*;
import org.hibernate.service.spi.InjectService;

import java.util.*;

public class RecommenderService {

    final private RatingRepository ratingRepository;
    public RecommenderService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    // cos similarity of 2 users ratings
    public double cosineSimilarity(int[] ratingsUser1, int[] ratingsUser2) {
        double dotProduct = 0.0;
        double normUser1 = 0.0;
        double normUser2 = 0.0;

        for (int i = 0; i < ratingsUser1.length; i++) {
            dotProduct += ratingsUser1[i] * ratingsUser2[i];
            normUser1 += Math.pow(ratingsUser1[i], 2);
            normUser2 += Math.pow(ratingsUser2[i], 2);
        }

        return dotProduct / (Math.sqrt(normUser1) * Math.sqrt(normUser2));
    }
    public List<Item> recommendItems(User targetUser) {

        // 1. Obtain ratings for the given user
        List<Rating> targetUserRatings = ratingRepository.findByUser(targetUser);

        // 2. Find similar users
        List<User> similarUsers = ratingRepository.findSimilarUsers(targetUser);

        List<Item> recommendedItems = new ArrayList<>();

        // 3. Iterate similar user and evaluate recommendations
        for (User similarUser : similarUsers) {
            List<Rating> similarUserRatings = ratingRepository.findByUser(similarUser);

            for (Rating rating : similarUserRatings) {
                // target user hasn't evaluated this and  rating is >= 4
                boolean alreadyRated = targetUserRatings.stream()
                        .anyMatch(tr -> tr.getItem().equals(rating.getItem()));

                if (!alreadyRated && rating.getRating() >= 4) {
                    recommendedItems.add(rating.getItem());
                }
            }
        }

        return recommendedItems;
    }


}