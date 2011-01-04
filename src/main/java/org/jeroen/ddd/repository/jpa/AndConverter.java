package org.jeroen.ddd.repository.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.jeroen.ddd.specification.AndSpecification;


public class AndConverter implements SpecificationConverter<AndSpecification<Object>, Object> {
    private final SpecificationTranslator translator;

    public AndConverter(SpecificationTranslator translator) {
        super();
        this.translator = translator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Predicate convert(AndSpecification<Object> spec, Root<Object> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        Predicate lhsPredicate = translator.translateToPredicate(spec.getLhs(), root, cq, cb);
        Predicate rhsPredicate = translator.translateToPredicate(spec.getRhs(), root, cq, cb);
        return cb.and(lhsPredicate, rhsPredicate);
    }

}