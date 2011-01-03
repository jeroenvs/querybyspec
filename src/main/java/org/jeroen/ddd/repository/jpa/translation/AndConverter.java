package org.jeroen.ddd.repository.jpa.translation;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.jeroen.ddd.specification.AndSpecification;


public class AndConverter implements SpecificationToPredicateConverter<AndSpecification<Object>, Object> {
    private final SpecificationToPredicateTranslator translator;

    public AndConverter(SpecificationToPredicateTranslator translator) {
        super();
        this.translator = translator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Predicate convert(AndSpecification<Object> specification, Root<Object> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        Predicate lhsPredicate = translator.translate(specification.getLhs(), root, cq, cb);
        Predicate rhsPredicate = translator.translate(specification.getRhs(), root, cq, cb);
        return cb.and(lhsPredicate, rhsPredicate);
    }

}