package nl.mad.hactar.specification;

import static nl.mad.hactar.specification.SpecificationBuilder.equal;
import static nl.mad.hactar.specification.SpecificationBuilder.not;

import java.util.Arrays;
import java.util.List;

import nl.mad.hactar.domain.Post;
import nl.mad.hactar.specification.ComposableSpecification;
import nl.mad.hactar.specification.Specification;
import nl.mad.hactar.specification.Specifications;

import org.junit.Assert;
import org.junit.Test;

public class SpecificationTest {

    // TODO Quick unit test setup, still needs to be decomposed in separate classes and scenarios

    @Test
    public void testSpecifications() {
        Post post = new Post().setMessage("test");
        ComposableSpecification<Post> specification = equal("message", "test");
        Assert.assertTrue(specification.isSatisfiedBy(post));
        Assert.assertFalse(not(specification).isSatisfiedBy(post));
        Assert.assertTrue(specification.or(not(specification)).isSatisfiedBy(post));
        Assert.assertFalse(specification.and(not(specification)).isSatisfiedBy(post));
    }

    @Test
    public void testValidationAndSelection() {
        Post post = new Post().setMessage("test");
        Post another = new Post().setMessage("another");
        List<Post> posts = Arrays.asList(post, another);
        Specification<Post> specification = equal("message", "test");
        Specifications<Post> helper = new Specifications<Post>(specification);
        Assert.assertFalse(helper.isSatisfiedByAll(posts));
        List<Post> satisfyingPosts = helper.findMatches(posts);
        Assert.assertEquals(Arrays.asList(post), satisfyingPosts);
        Assert.assertTrue(helper.isSatisfiedByAll(satisfyingPosts));
    }

}
