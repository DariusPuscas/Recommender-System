package com.recommender.controller;
import org.json.JSONObject;
import com.recommender.JWTUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        if (email != null && !email.isEmpty()) {
            // Generate JWT token
            String token = JWTUtil.generateToken(email);

            // simulates items ;
            String[] recommendedItems = {"Smartphone", "Laptop", "Book"};

            // Create json response
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("token", token);
            jsonResponse.put("recommendedItems", recommendedItems);
            out.print(jsonResponse);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("message", "Invalid email");
            out.print(jsonResponse);
        }
    }
}
