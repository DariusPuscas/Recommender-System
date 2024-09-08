package com.recommender.controller;

import com.google.gson.Gson;
import com.recommender.model.Item;
import com.recommender.model.User;
import com.recommender.service.RecommenderService;
import com.recommender.repository.RatingRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/recommendations/*")
public class RecommendationServlet extends HttpServlet {

    private RecommenderService recommenderService = new RecommenderService(new RatingRepository());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo(); // /{userId}
        if (pathInfo == null || pathInfo.length() <= 1) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "User ID is missing");
            return;
        }

        // Extract userId from URL
        int userId = Integer.parseInt(pathInfo.substring(3));
        User user = new User();
        user.setUserid(userId);  // Setăm userId-ul pentru utilizatorul cerut

        // get recommendations for the given user
        List<Item> recommendations = recommenderService.recommendItems(user);

        // Convert recommendations list in JSON
        String jsonResponse = new Gson().toJson(recommendations);

        //  HTTP response
        resp.setContentType("application/json");
        resp.getWriter().write(jsonResponse);
    }
}