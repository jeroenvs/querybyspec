package nl.mad.hactar.repository.jpa.translation;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import nl.mad.hactar.specification.EqualSpecification;

public class EqualConverter implements SpecificationToPredicateConverter<EqualSpecification<Object>, Object> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Predicate convert(EqualSpecification<Object> spec, Root<Object> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        return cb.equal(root.get(spec.getProperty()), spec.getValue());
    }

}
