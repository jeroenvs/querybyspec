package nl.mad.hactar.repository;

import java.util.Collection;
import java.util.List;

import nl.mad.hactar.specification.Specification;

/**
 * Default implementation of the {@link Repository}. Delegates any
 * data access and modification behaviour to an internal strategy.
 * 
 * @author Jeroen van Schagen
 * @since 29-12-2010
 *
 * @param <T> type of domain objects being managed
 */
public class RepositoryImpl<T> implements Repository<T> {
    private RepositoryStrategy<T> strategy;

    /**
     * Construct a new {@link RepositoryImpl}.
     */
    public RepositoryImpl() {
        super();
    }

    /**
     * Construct a new {@link RepositoryImpl}, for the specified entities.
     * @param entities the entities that should be managable
     */
    public RepositoryImpl(Collection<T> entities) {
        super();
        strategy = new InMemoryRepositoryStrategy<T>(entities);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> matching(Specification<T> specification) {
        return strategy.matching(specification);
    }

    // Attribute access

    public Repository<T> setStrategy(RepositoryStrategy<T> strategy) {
        this.strategy = strategy;
        return this;
    }

    public RepositoryStrategy<T> getStrategy() {
        return strategy;
    }

}
