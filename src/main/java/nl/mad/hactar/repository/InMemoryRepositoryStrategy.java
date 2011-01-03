package nl.mad.hactar.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.mad.hactar.specification.Specification;

public class InMemoryRepositoryStrategy<T> implements RepositoryStrategy<T> {
    private final Set<T> entities;

    public InMemoryRepositoryStrategy(Collection<T> entities) {
        this.entities = new HashSet<T>(entities);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> matching(Specification<T> specification) {
        List<T> matching = new ArrayList<T>();
        for (T candidate : entities) {
            if (specification.isSatisfiedBy(candidate)) {
                matching.add(candidate);
            }
        }
        return matching;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long countBy(Specification<T> specification) {
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
    public boolean hasAny(Specification<T> specification) {
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
