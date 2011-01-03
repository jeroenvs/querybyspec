package nl.mad.hactar.repository.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import nl.mad.hactar.specification.Specification;

/**
 * Java Persistence API specific implementation of {@link Specification}.
 * Specifications that implement this interface are capable of translating
 * themselves into a {@link Predicate}, which enforces only satisfying entities
 * to be returned.
 * 
 * @author Jeroen van Schagen
 * @since 28-12-2010
 * 
 * @param <T> type of entity represented in our specification logic
 */
public interface PredicateSpecification<T> extends Specification<T> {

    /**
     * Construct a {@link Predicate} that enforces this specification's criteria.
     * 
     * @param root path to the root of our query
     * @param cq query that will hold our specification criteria
     * @param cb criteria builder, used to construct predicates
     * @return the converted predicate
     */
    Predicate toPredicate(Root<T> root, CriteriaQuery<?> cq, CriteriaBuilder cb);

}
