package nl.mad.hactar.repository.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import nl.mad.hactar.domain.Post;
import nl.mad.hactar.domain.Post_;

/**
 * {@link HasTestMessageSpecification} that knows how to construct a corresponding {@link Predicate}.
 * @author Jeroen van Schagen
 * @since 28-12-2010
 */
public class HasTestMessageSpecificationJpa extends HasTestMessageSpecification implements PredicateSpecification<Post> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        return cb.equal(root.get(Post_.message), "test");
    }

}
