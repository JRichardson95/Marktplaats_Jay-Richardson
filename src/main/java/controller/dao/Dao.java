package controller.dao;

import model.entity.Identifiable;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class Dao<T extends Identifiable<I>, I> {
    private final EntityManager ENTITY_MANAGER;

    public Dao(EntityManager ENTITY_MANAGER) {
        this.ENTITY_MANAGER = ENTITY_MANAGER;
    }

    public T find(I id) {
        return ENTITY_MANAGER.find(T(), id);
    }

    public List<T> findAll() {
        return ENTITY_MANAGER.createNamedQuery(typeSimple() + ".findAll", T()).getResultList(); // JPQL Java Persistence Query Language
    }

    public void save(T p) {
        ENTITY_MANAGER.getTransaction().begin();
        ENTITY_MANAGER.persist(p);
        ENTITY_MANAGER.getTransaction().commit();
    }

    public T update(T e) {
        ENTITY_MANAGER.getTransaction().begin();
        T mergedT = ENTITY_MANAGER.merge(e);
        ENTITY_MANAGER.getTransaction().commit();
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


