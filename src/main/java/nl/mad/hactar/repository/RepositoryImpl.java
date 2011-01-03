package nl.mad.hactar.repository;

import java.util.List;

import nl.mad.hactar.specification.Specification;

public class RepositoryImpl<T> implements Repository<T> {
    private RepositoryStrategy<T> strategy;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> matches(Specification<T> specification) {
        return strategy.matches(specification);
    }

    public Repository<T> setStrategy(RepositoryStrategy<T> strategy) {
        this.strategy = strategy;
        return this;
    }

    public RepositoryStrategy<T> getStrategy() {
        return strategy;
    }

}
