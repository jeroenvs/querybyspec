package org.jeroen.ddd.repository;

import java.util.ArrayList;
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
        List<T> matches = new ArrayList<T>();
        for (T candidate : entities) {
            if (specification.isSatisfiedBy(candidate)) {
                matches.add(candidate);
            }
        }
        return matches;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long howMany(Specification<T> specification) {
        long count = 0;
        for (T candidate : entities) {
            if (specification.isSatisfiedBy(candidate)) {
                count++;
            }
        }
        return count;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsAny(Specification<T> specification) {
        for (T candidate : entities) {
            if (specification.isSatisfiedBy(candidate)) {
                return true;
            }
        }
        return false;
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
