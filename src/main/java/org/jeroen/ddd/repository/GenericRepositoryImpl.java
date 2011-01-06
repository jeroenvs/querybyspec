package org.jeroen.ddd.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jeroen.ddd.specification.Specification;

/**
 * Default implementation of the {@link GenericRepository}. Delegates any
 * data access and modification behaviour to an internal strategy.
 * 
 * @author Jeroen van Schagen
 * @since 29-12-2010
 *
 * @param <T> type of entities being managed
 */
public class GenericRepositoryImpl<T> implements GenericRepository<T> {
    private RepositoryStrategy<T> strategy;

    /**
     * Construct a new {@link GenericRepositoryImpl}.
     */
    public GenericRepositoryImpl() {
        super();
    }

    /**
     * Construct a new {@link GenericRepositoryImpl}, for the specified entities.
     * @param strategy the strategy that should be applied
     */
    public GenericRepositoryImpl(RepositoryStrategy<T> strategy) {
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
     * {@inheritDoc}
     */
    @Override
    public long countBy(Specification<T> specification) {
        return strategy.countBy(specification);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasAny(Specification<T> specification) {
        return strategy.hasAny(specification);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R extends T> R add(R entity) {
        return strategy.add(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final <R extends T> List<R> addAll(Collection<R> entities) {
        List<R> result = new ArrayList<R>();
        for (R entity : entities) {
            result.add(this.add(entity));
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(T entity) {
        strategy.remove(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
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

    public RepositoryStrategy<T> getStrategy() {
        return strategy;
    }

}
