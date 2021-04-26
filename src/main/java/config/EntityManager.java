package config;

import javax.persistence.Persistence;

public class EntityManager {
    public static final javax.persistence.EntityManager em =
            Persistence.createEntityManagerFactory("MySQL-Marktplaats").createEntityManager();
}
