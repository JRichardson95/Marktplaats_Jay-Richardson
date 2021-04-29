package controller.dao;
import model.entity.Advertentie;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AdvertentieDao extends Dao<Advertentie, Long> {

    private EntityManager entityManager;
    private static AdvertentieDao instance;

    public AdvertentieDao(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }
    public static AdvertentieDao instance (EntityManager em){
        if (instance == null) {
            instance = new AdvertentieDao(em);
        }
        return instance;
    }

    public List<Advertentie> findByGebruikerId(Long id){
        TypedQuery<Advertentie> query = entityManager.createQuery("Select a from Advertentie a where a.gebruiker.id=:gebruikerId", Advertentie.class);
        query.setParameter("gebruikerId", id);
        return query.getResultList();
    }

}
