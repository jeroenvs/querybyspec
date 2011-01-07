package org.jeroen.ddd.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jeroen.ddd.specification.Specification;

/**
 * Generic repository, can be used to store and retrieve entities of any type. The
 * implementation delegates all data behaviour to an internal strategy, which can
 * be manipulated during runtime.
 * 
 * @author Jeroen van Schagen
 * @since 29-12-2010
 *
 * @param <T> type of entities being managed
 */
public class GenericRepository<T> implements FlexibleRepository<T> {
    private RepositoryStrategy<T> strategy;

    /**
     * Construct a new {@link GenericRepository}.
     */
    public GenericRepository() {
        super();
    }

    /**
     * Construct a new {@link GenericRepository}, for the specified entities.
     * @param strategy the strategy that should be applied
     */
    public GenericRepository(RepositoryStrategy<T> strategy) {
        super();
        this.strategy = strategy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> matching(Specification<T> specification) {
        return strategy.matching(specification);
    }

    /**
     * Count how many entities match a specification.
     * @param specification
     * @return
     */
    public long countBy(Specification<T> specification) {
        return strategy.countBy(specification);
    }

    /**
     * Determine if any of our entities match a specification.
     * @param specification
     * @return
     */
    public boolean hasAny(Specification<T> specification) {
        return strategy.hasAny(specification);
    }

    /**
     * Store an entity, enabling it to be accessed.
     * @param <R>
     * @param entity
     * @return
     */
    public <R extends T> R add(R entity) {
        return strategy.add(entity);
    }

    /**
     * Store a collection of entities, enabling them to be accessed.
     * @param <R>
     * @param entities
     * @return
     */
    public <R extends T> List<R> addAll(Collection<R> entities) {
        List<R> result = new ArrayList<R>();
        for (R entity : entities) {
            result.add(this.add(entity));
        }
        return result;
    }

    /**
     * Remove an entities, preventing it to be accessed.
     * @param entity
     */
    public void remove(T entity) {
        strategy.remove(entity);
    }

    /**
     * Remove any entity that matches the provided specification.
     * @param specification
     * @return list of removed entities
     */
    public List<T> removeAny(Specification<T> specification) {
        List<T> entities = this.matching(specification);
        for (T entity : entities) {
            this.remove(entity);
        }
        return entities;
    }

    // Attribute access

    public GenericRepository<T> setStrategy(RepositoryStrategy<T> strategy) {
        this.strategy = strategy;
        return this;
    }

}
