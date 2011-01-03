package nl.mad.hactar.repository.jpa.translation;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import nl.mad.hactar.specification.AndSpecification;

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
        Predicate lhsPredicate = translator.translateToPredicate(specification.getLhs(), root, cq, cb);
        Predicate rhsPredicate = translator.translateToPredicate(specification.getRhs(), root, cq, cb);
        return cb.and(lhsPredicate, rhsPredicate);
    }

}