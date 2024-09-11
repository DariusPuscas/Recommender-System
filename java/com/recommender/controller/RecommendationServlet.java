package com.recommender.controller;

import com.google.gson.Gson;
import com.recommender.model.Item;
import com.recommender.model.User;
import com.recommender.service.RecommenderService;
import com.recommender.repository.RatingRepository;
import com.recommender.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/recommendations/*")
public class RecommendationServlet extends HttpServlet {

    final private RecommenderService recommenderService = new RecommenderService(new RatingRepository());
    final private UserRepository userRepository = new UserRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Received GET request on RecommendationServlet");
        String pathInfo = req.getPathInfo(); // /{userId}
        if (pathInfo == null || pathInfo.length() <= 1) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "User ID is missing");
            return;
        }

        // Extract userId from URL
        int userId = Integer.parseInt(pathInfo.substring(1));
        System.out.println("User ID: " + userId);
        // Fetch the actual User object from the database
        User user = userRepository.findById(userId);
        if (user == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
            return;
        }

        // get recommendations for the given user
        List<Item> recommendations = recommenderService.recommendItems(user);

        // Convert recommendations list in JSON
        String jsonResponse = new Gson().toJson(recommendations);

        //  HTTP response
        resp.setContentType("application/json");
        resp.getWriter().write(jsonResponse);
    }
}