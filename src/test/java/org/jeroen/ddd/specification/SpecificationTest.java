package org.jeroen.ddd.specification;

import static org.jeroen.ddd.specification.Specifications.not;

import java.util.Arrays;
import java.util.List;

import org.jeroen.ddd.domain.Post;
import org.junit.Assert;
import org.junit.Test;

public class SpecificationTest {

    // TODO Quick unit test setup, still needs to be decomposed in separate classes and scenarios

    @Test
    public void testSpecifications() {
        Post post = new Post().setMessage("test");
        ComposableSpecification<Post> specification = new EqualitySpecification<Post>("message", "test");
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
        Specification<Post> specification = new EqualitySpecification<Post>("message", "test");
        Assert.assertFalse(SpecificationUtils.isSatisfiedByAll(specification, posts));
        List<Post> satisfyingPosts = SpecificationUtils.selectMatches(specification, posts);
        Assert.assertEquals(Arrays.asList(post), satisfyingPosts);
        Assert.assertTrue(SpecificationUtils.isSatisfiedByAll(specification, satisfyingPosts));
    }

}
