package com.store.dao;

import java.util.List;

/**
 * Data Access Object Interface 
 * 
 * @author Jacek
 *
 * @param <K>
 * @param <E>
 */
public interface IDao<K, E> {

    void persist(E entity);

    void remove(E entity);

    E findById(K id);
    
    List<E> getAll();
}
