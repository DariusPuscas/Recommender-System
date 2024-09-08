package com.recommender.controller;

import com.google.gson.Gson;
import com.recommender.model.Rating;
import com.recommender.model.User;
import com.recommender.repository.RatingRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ratings/user/*")
public class RatingServlet extends HttpServlet {

    private RatingRepository ratingRepository = new RatingRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo(); // /{userId}
        if (pathInfo == null || pathInfo.length() <= 1) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "User ID is missing");
            return;
        }

        // extract userId from URL
        int userId = Integer.parseInt(pathInfo.substring(3));
        User user = new User();
        user.setUserid(userId);  // Set userId for the chosen user

        // get evaluations of the selected user
        List<Rating> ratings = ratingRepository.findByUser(user);

        // convert the list of evaluations to JSON
        String jsonResponse = new Gson().toJson(ratings);

        // HTTP response
        resp.setContentType("application/json");
        resp.getWriter().write(jsonResponse);
    }
}