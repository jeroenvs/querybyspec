package nl.mad.hactar.repository.jpa.translation;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import nl.mad.hactar.specification.OrSpecification;

public class OrConverter implements SpecificationToPredicateConverter<OrSpecification<Object>, Object> {
    private final SpecificationToPredicateTranslator translator;

    public OrConverter(SpecificationToPredicateTranslator translator) {
        super();
        this.translator = translator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Predicate convert(OrSpecification<Object> specification, Root<Object> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        Predicate lhsPredicate = translator.translate(specification.getLhs(), root, cq, cb);
        Predicate rhsPredicate = translator.translate(specification.getRhs(), root, cq, cb);
        return cb.or(lhsPredicate, rhsPredicate);
    }

}