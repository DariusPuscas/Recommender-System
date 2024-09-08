package org.example;

import com.recommender.controller.*;

import jakarta.servlet.Servlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Main {

    public static void main(String[] args) throws LifecycleException {

        // Inițializare Tomcat
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        // Configurarea directorului de lucru pentru Tomcat
        tomcat.setBaseDir(".");

        // Adaugam un context pentru aplicația web
        Context ctx = tomcat.addContext("", new File(".").getAbsolutePath());

        // Adaugam servlet-ul pentru evaluari (ratings)
        Tomcat.addServlet(ctx, "ratingServlet", RatingServlet.class.getName());
        ctx.addServletMappingDecoded("/ratings/user/*", "ratingServlet");

        // Adaugam servlet-ul pentru recomandari
        Tomcat.addServlet(ctx, "recommendationServlet", RecommendationServlet.class.getName());
        ctx.addServletMappingDecoded("/recommendations/*", "recommendationServlet");

        // Pornim serverul Tomcat
        tomcat.start();
        tomcat.getServer().await();
    }
}
