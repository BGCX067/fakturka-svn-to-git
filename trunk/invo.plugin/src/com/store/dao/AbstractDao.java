package com.store.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Main Data Access Object
 * 
 * @author Jacek
 *
 * @param <K>
 * @param <E>
 */
public abstract class AbstractDao<K, E> implements IDao<K, E> {

    protected Class<E> entityClass;

    protected EntityManager entityManager;

    public AbstractDao() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
    }

    /**
     * funkcja Zapisz
     */
    public void persist(E entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    /**
     * funkcja Usuñ
     */
    public void remove(E entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    /**
     * Pobierz listê 
     */
    public List<E> getAll() {
        CriteriaQuery<E> criteriaQuery =  entityManager.getCriteriaBuilder().createQuery(entityClass);
        Root<E> from = criteriaQuery.from(entityClass);
        CriteriaQuery<E> select = criteriaQuery.select(from);
        TypedQuery<E> typedQuery = entityManager.createQuery(select);
        return typedQuery.getResultList();
    }
    
    /**
     * Szukaj po kluczu
     */
    public E findById(K id) {
        return entityManager.find(entityClass, id);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
