package org.jeroen.ddd.repository.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.jeroen.ddd.specification.OrSpecification;

public class OrConverter implements SpecificationConverter<OrSpecification<Object>, Object> {
    private final SpecificationTranslator translator;

    public OrConverter(SpecificationTranslator translator) {
        super();
        this.translator = translator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Predicate convertToPredicate(OrSpecification<Object> or, Root<Object> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        Predicate lhs = translator.translateToPredicate(or.getLhs(), root, cq, cb);
        Predicate rhs = translator.translateToPredicate(or.getRhs(), root, cq, cb);
        return cb.or(lhs, rhs);
    }

}