package nl.mad.hactar.repository;

import java.util.List;

import nl.mad.hactar.specification.Specification;

public interface Repository<T> {

    List<T> matching(Specification<T> specification);

}
