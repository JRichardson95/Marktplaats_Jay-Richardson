package controller.dao;

import model.entity.Gebruiker;

import javax.persistence.EntityManager;

public class GebruikersDao extends Dao<Gebruiker, Long> {
    private static GebruikersDao instance;

    public GebruikersDao(EntityManager entityManager) {
        super(entityManager);
    }

    public static GebruikersDao instance (EntityManager em){
        if (instance == null) {
            instance = new GebruikersDao(em);
        }
        return instance;
    }
}

