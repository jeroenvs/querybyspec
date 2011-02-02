package org.jeroen.ddd.repository;

import java.util.List;

import org.jeroen.ddd.specification.Specification;

/**
 * Strategy used inside a repository.
 * 
 * @author Jeroen van Schagen
 * @since 29-12-2010
 *
 * @param <T> type of domain objects being managed
 */
public interface RepositoryStrategy<T> {

    // Access

    /**
     * Retrieve all entities that match a specification.
     * @param specification
     * @return
     */
    List<T> matching(Specification<T> specification);

    /**
     * Count how many entities match a specification.
     * @param specification
     * @return
     */
    long howMany(Specification<T> specification);

    /**
     * Determine if any of our entities match a specification.
     * @param specification
     * @return
     */
    boolean containsAny(Specification<T> specification);

    // Modification

    /**
     * Store an entity, enabling it to be accessed.
     * @param <R>
     * @param entity
     * @return
     */
    <R extends T> R add(R entity);

    /**
     * Remove an entities, preventing it to be accessed.
     * @param entity
     */
    void remove(T entity);

}
