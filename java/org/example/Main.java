package org.example;

import com.recommender.controller.*;

//import jakarta.servlet.Servlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Main {

    public static void main(String[] args) throws LifecycleException {
        // Inițializare Tomcat
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8081);
        System.out.println("Tomcat initialized");

        // Configurarea directorului de lucru pentru Tomcat
        tomcat.setBaseDir(".");
        System.out.println("Tomcat base directory set");

        // context
        Context ctx = tomcat.addContext("", new File(".").getAbsolutePath());
        System.out.println("Context added to Tomcat");

        // servlet-ul pentru evaluari (ratings)
        Tomcat.addServlet(ctx, "ratingServlet", new RatingServlet());
        ctx.addServletMappingDecoded("/ratings/user/*", "ratingServlet");
        System.out.println("RatingServlet added");

        //  servlet-ul pentru recomandari
        Tomcat.addServlet(ctx, "recommendationServlet", new RecommendationServlet());
        ctx.addServletMappingDecoded("/recommendations/*", "recommendationServlet");
        System.out.println("RecommendationServlet added");

        Tomcat.addServlet(ctx, "testServlet", new TestServlet());
        ctx.addServletMappingDecoded("/test/*", "testServlet");
        System.out.println("TestServlet added");

        System.setProperty("org.apache.catalina.startup.EXIT_ON_INIT_FAILURE", "true");
        System.setProperty("java.util.logging.ConsoleHandler.level", "ALL");

        tomcat.getConnector().setAttribute("address", "0.0.0.0");

        // start Tomcat server
        tomcat.start();
        System.out.println("Tomcat started");

        tomcat.getServer().await();
    }

}
