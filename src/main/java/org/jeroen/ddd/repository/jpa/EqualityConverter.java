package org.jeroen.ddd.repository.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.jeroen.ddd.specification.basic.EqualitySpecification;

public class EqualityConverter implements SpecificationConverter<EqualitySpecification<Object>, Object> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Predicate convertToPredicate(EqualitySpecification<Object> eq, Root<Object> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        return cb.equal(root.get(eq.getProperty()), eq.getValue());
    }

}
