package nl.mad.hactar.repository.jpa.translation;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import nl.mad.hactar.specification.Specification;

/**
 * Specifies the conversion logic of a {@link Specification} into {@link Predicate}.
 * 
 * @param <T> type of entity, used in the specification
 * @param <S> type of specification being converted
 * 
 * @author Jeroen van Schagen
 * @since 28-12-2010
 */
public interface SpecificationToPredicateConverter<S extends Specification<T>, T> {

    /**
     * Convert a {@link Specification} into a Java Persistence API {@link Predicate}.
     * 
     * @param specification our business specification being converted
     * @param root path to the root of our query
     * @param cq query that will hold our specification criteria
     * @param cb criteria builder, used to construct predicates
     * @return the converted predicate
     */
    Predicate convert(S specification, Root<T> root, CriteriaQuery<?> cq, CriteriaBuilder cb);

}
