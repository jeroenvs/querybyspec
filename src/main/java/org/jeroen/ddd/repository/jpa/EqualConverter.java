package org.jeroen.ddd.repository.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.jeroen.ddd.specification.EqualitySpecification;


public class EqualConverter implements SpecificationConverter<EqualitySpecification<Object>, Object> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Predicate convert(EqualitySpecification<Object> spec, Root<Object> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        return cb.equal(root.get(spec.getProperty()), spec.getValue());
    }

}
