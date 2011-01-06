package org.jeroen.ddd.repository;

import java.util.Collection;
import java.util.List;

import org.jeroen.ddd.specification.Specification;

public interface GenericRepository<T> extends FlexibleRepository<T> {

    /**
     * Count how many entities match a specification.
     * @param specification
     * @return
     */
    long countBy(Specification<T> specification);

    /**
     * Determine if any of our entities match a specification.
     * @param specification
     * @return
     */
    boolean hasAny(Specification<T> specification);

    /**
     * Store an entity, enabling it to be accessed.
     * @param <R>
     * @param entity
     * @return
     */
    <R extends T> R add(R entity);

    /**
     * Store a collection of entities, enabling them to be accessed.
     * @param <R>
     * @param entities
     * @return
     */
    <R extends T> List<R> addAll(Collection<R> entities);

    /**
     * Remove an entities, preventing it to be accessed.
     * @param entity
     */
    void remove(T entity);

    /**
     * Remove any entity that matches the provided specification.
     * @param specification
     * @return list of removed entities
     */
    List<T> removeAny(Specification<T> specification);

}
