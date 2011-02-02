package org.jeroen.ddd.repository;

import static org.jeroen.ddd.specification.Specifications.countAllSatisfying;
import static org.jeroen.ddd.specification.Specifications.findAllSatisfying;
import static org.jeroen.ddd.specification.Specifications.isSatisfiedBySome;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jeroen.ddd.specification.Specification;

/**
 * In-memory specific implementation of the {@link RepositoryStrategy} interface. This strategy
 * maintains reference to zero or more entities, retrieving and modifying them manually.
 * 
 * @author Jeroen van Schagen
 * @since 29-12-2010
 *
 * @param <T> the type of our entities being managed
 */
public class InMemoryRepositoryStrategy<T> implements RepositoryStrategy<T> {
    private final Set<T> entities;

    /**
     * Construct a new {@link InMemoryRepositoryStrategy}.
     * @param entities the entities that should initially be retrievable
     */
    public InMemoryRepositoryStrategy(T... entities) {
        this(Arrays.asList(entities));
    }

    /**
     * Construct a new {@link InMemoryRepositoryStrategy}.
     * @param entities the entities that should initially be retrievable
     */
    public InMemoryRepositoryStrategy(Collection<T> entities) {
        super();
        this.entities = new HashSet<T>(entities);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> matching(Specification<T> specification) {
        return findAllSatisfying(specification, entities);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long howMany(Specification<T> specification) {
        return countAllSatisfying(specification, entities);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsAny(Specification<T> specification) {
        return isSatisfiedBySome(specification, entities);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R extends T> R add(R entity) {
        entities.add(entity);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(T entity) {
        entities.remove(entity);
    }

}
