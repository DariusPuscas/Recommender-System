package org.example;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.recommender.model.User;

public class Main {
    public static void main(String[] args) {
        // Configure hibernate; init. SessionFactory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml") // fișierul tău de configurare
                .addAnnotatedClass(User.class)  // clasa ta model User
                .buildSessionFactory();

        // Crearea unei sesiuni
        Session session = factory.getCurrentSession();

        try {
            // Începerea tranzacției
            session.beginTransaction();

            // Crearea unui nou utilizator
            User newUser = new User();
            newUser.setUsername("john_doe");
            newUser.setUseremail("john@example.com");

            // Salvarea utilizatorului în baza de date
            session.save(newUser);

            // Finalizarea tranzacției
            session.getTransaction().commit();

            System.out.println("Utilizator salvat cu succes!");

        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            factory.close();  // Închide fabrica de sesiuni
        }
    }
}
