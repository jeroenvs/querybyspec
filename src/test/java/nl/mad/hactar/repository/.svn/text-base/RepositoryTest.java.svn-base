package nl.mad.hactar.repository;

import java.util.Arrays;
import java.util.List;

import nl.mad.hactar.domain.Post;
import nl.mad.hactar.specification.EqualSpecification;

import org.junit.Assert;
import org.junit.Test;

public class RepositoryTest {

    @Test
    public void testInMemory() {
        Post firstPost = new Post().setMessage("first");
        Post secondPost = new Post().setMessage("second");
        Repository<Post> postRepository = RepositoryFactory.forCollection(Arrays.asList(firstPost, secondPost));
        List<Post> matchingPosts = postRepository.matches(new EqualSpecification<Post>("message", "first"));
        Assert.assertEquals(Arrays.asList(firstPost), matchingPosts);
    }

}
