package config;

import javax.persistence.Persistence;

public class EntityManager {
    public static final javax.persistence.EntityManager EM =
            Persistence.createEntityManagerFactory("MySQL-Marktplaats").createEntityManager();
}
