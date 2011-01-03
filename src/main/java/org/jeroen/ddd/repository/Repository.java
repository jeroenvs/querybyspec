package org.jeroen.ddd.repository;

import java.util.List;

import org.jeroen.ddd.specification.Specification;


public interface Repository<T> {

    List<T> matching(Specification<T> specification);

}
