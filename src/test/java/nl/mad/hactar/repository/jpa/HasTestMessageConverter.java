package nl.mad.hactar.repository.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import nl.mad.hactar.domain.Post;
import nl.mad.hactar.domain.Post_;
import nl.mad.hactar.repository.jpa.translation.RegisteredAutomatically;
import nl.mad.hactar.repository.jpa.translation.SpecificationToPredicateConverter;

/**
 * Converts a {@link HasTestMessage} into a {@link Predicate}.
 * @author Jeroen van Schagen
 * @since 28-12-2010
 */
@RegisteredAutomatically
public class HasTestMessageConverter implements SpecificationToPredicateConverter<HasTestMessage, Post> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Predicate convert(HasTestMessage specification, Root<Post> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        return cb.equal(root.get(Post_.message), "test");
    }

}
