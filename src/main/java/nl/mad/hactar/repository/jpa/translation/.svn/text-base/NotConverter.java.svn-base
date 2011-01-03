package nl.mad.hactar.repository.jpa.translation;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import nl.mad.hactar.specification.NotSpecification;

public class NotConverter implements SpecificationToPredicateConverter<NotSpecification<Object>, Object> {
    private final SpecificationToPredicateTranslator translator;

    public NotConverter(SpecificationToPredicateTranslator translator) {
        super();
        this.translator = translator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Predicate convert(NotSpecification<Object> spec, Root<Object> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        return cb.not(translator.translateToPredicate(spec.getProposition(), root, cq, cb));
    }

}
