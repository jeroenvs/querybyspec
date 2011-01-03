package nl.mad.hactar.repository.jpa;

import nl.mad.hactar.domain.Post;
import nl.mad.hactar.specification.ComposableSpecification;

/**
 * Specification that tests if a {@link Post} has the message value 'test'.
 * @author Jeroen van Schagen
 * @since 28-12-2010
 */
public class HasTestMessage extends ComposableSpecification<Post> {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfiedBy(Post candidate) {
        return candidate.getMessage().equals("test");
    }

}
