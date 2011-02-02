package org.jeroen.ddd.repository.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.jeroen.ddd.specification.basic.LessThanSpecification;

public class LessThanConverter implements SpecificationConverter<LessThanSpecification<Object>, Object> {

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public Predicate convertToPredicate(LessThanSpecification<Object> lt, Root<Object> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        return cb.lessThan(root.<Comparable> get(lt.getProperty()), (Comparable<?>) lt.getValue());
    }

}
