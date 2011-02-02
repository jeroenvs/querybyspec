package org.jeroen.ddd.repository.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.jeroen.ddd.specification.GreaterThanSpecification;

public class GreaterThanConverter implements SpecificationConverter<GreaterThanSpecification<Object>, Object> {

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public Predicate convertToPredicate(GreaterThanSpecification<Object> gt, Root<Object> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        return cb.greaterThan(root.<Comparable> get(gt.getProperty()), (Comparable<?>) gt.getValue());
    }

}
