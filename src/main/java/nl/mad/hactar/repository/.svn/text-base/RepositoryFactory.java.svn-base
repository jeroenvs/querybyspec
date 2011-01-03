package nl.mad.hactar.repository;

import java.util.Collection;

public final class RepositoryFactory {

    public static <T> Repository<T> forCollection(Collection<T> entities) {
        return new RepositoryImpl<T>().setStrategy(new InMemoryRepositoryStrategy<T>(entities));
    }

    // Hide constructor to prevent initialization
    private RepositoryFactory() {
        super();
    }

}
