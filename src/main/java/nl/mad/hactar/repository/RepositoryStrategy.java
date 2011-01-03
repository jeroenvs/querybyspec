package nl.mad.hactar.repository;

import java.util.List;

import nl.mad.hactar.specification.Specification;

public interface RepositoryStrategy<T> {

    List<T> matches(Specification<T> specification);

    long count(Specification<T> specification);

    boolean exists(Specification<T> specification);

    <R extends T> R add(R entity);

    void remove(T entity);

}
