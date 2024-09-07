package com.recommender.service;

import com.recommender.model.Rating;
import com.recommender.repository.RatingRepository;

import java.util.*;

public class RecommenderService {
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
    public List<Integer> recommendItems(int userId, List<Rating> ratings) {
        Map<Integer, List<Rating>> userRatingsMap = new HashMap<>();

        // Group ratings by user
        for (Rating rating : ratings) {
            userRatingsMap.computeIfAbsent(rating.getUser().getUserid(), k -> new ArrayList<>()).add(rating);
        }

        int[] targetUserRatings = extractRatingsForUser(userId, ratings);
        List<Integer> recommendedItems = new ArrayList<>();

        double maxSimilarity = -1;
        int mostSimilarUser = -1;

        // search for the most similar user
        for (Map.Entry<Integer, List<Rating>> entry : userRatingsMap.entrySet()) {
            if (entry.getKey() != userId) {
                int[] otherUserRatings = extractRatingsForUser(entry.getKey(), ratings);
                double similarity = cosineSimilarity(targetUserRatings, otherUserRatings);
                if (similarity > maxSimilarity) {
                    maxSimilarity = similarity;
                    mostSimilarUser = entry.getKey();
                }
            }
        }

        // gives to the most similar user
        if (mostSimilarUser != -1) {
            for (Rating rating : userRatingsMap.get(mostSimilarUser)) {
                if (rating.getRating() >= 4) { // Recomandă articole cu rating mare
                    recommendedItems.add(rating.getItem().getItemId());
                }
            }
        }

        return recommendedItems;
    }

    private int[] extractRatingsForUser(int userId, List<Rating> ratings) {
        return ratings.stream()
                .filter(rating -> rating.getUser().getUserid() == userId)
                .mapToInt(Rating::getRating)
                .toArray();
    }
}