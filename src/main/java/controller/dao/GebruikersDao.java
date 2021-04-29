package controller.dao;

import model.entity.Gebruiker;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;



public class GebruikersDao extends Dao<Gebruiker, Long> {
    EntityManager entityManager;
    private static GebruikersDao instance;

    public GebruikersDao(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    public static GebruikersDao instance (EntityManager em){
        if (instance == null) {
            instance = new GebruikersDao(em);
        }
        return instance;
    }

    public Gebruiker findByEmail(String email) {
        TypedQuery<Gebruiker> query = entityManager.createQuery("Select g from Gebruiker g where g.email=:gebruikersEmail", Gebruiker.class);
        query.setParameter("gebruikersEmail", email);
        return query.getSingleResult();
    }

    public boolean existsByEmail(String email) {
        TypedQuery<Gebruiker> query = entityManager.createQuery("select g from Gebruiker g where g.email=:gebruikerEmail", Gebruiker.class);
        query.setParameter("gebruikerEmail", email);
        try {
            return query.getSingleResult() == null;
        } catch (NoResultException e) {
            return true;
        }
    }
}

