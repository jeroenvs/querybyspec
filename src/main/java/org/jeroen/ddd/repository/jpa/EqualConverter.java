package org.jeroen.ddd.repository.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.jeroen.ddd.specification.EqualSpecification;


public class EqualConverter implements SpecificationConverter<EqualSpecification<Object>, Object> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Predicate convert(EqualSpecification<Object> spec, Root<Object> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        return cb.equal(root.get(spec.getProperty()), spec.getValue());
    }

}
