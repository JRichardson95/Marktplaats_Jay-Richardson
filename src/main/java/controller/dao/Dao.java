package controller.dao;

import model.entity.Identifiable;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class Dao <T extends Identifiable<I>, I>{
    private final EntityManager entityManager;

    public Dao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public T find(I id){ return entityManager.find(T(), id);}

    public List<T> findAll() {
        return entityManager.createNamedQuery(typeSimple() + ".findAll", T()).getResultList(); // JPQL Java Persistence Query Language
    }

    public void save(T p) {
        entityManager.getTransaction().begin();
        entityManager.persist(p);
        entityManager.getTransaction().commit();
    }

    public T update(T e) {
        entityManager.getTransaction().begin();
        T mergedT = entityManager.merge(e);
        entityManager.getTransaction().commit();
        return mergedT;
    }


    /**
     * @return a class instance of the first generic type parameter (E) of this Dao,
     * e.g. for PersonDao<Person>, it returns Employee.class.
     */
    @SuppressWarnings("unchecked")
    private Class<T> T() {
        ParameterizedType thisDaoClass = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<T>) thisDaoClass.getActualTypeArguments()[0];
    }

    private String typeSimple() { return T().getSimpleName(); }

}


