package application;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {
public Application() {
    }

    public static void main(String[] args) {
        EntityManagerFactory ef = Persistence.createEntityManagerFactory("SQLdb");
        EntityManager em = ef.createEntityManager();
        ef.close();
        em.close();
    }

}
