package org.jeroen.ddd.repository.jpa;

import static org.jeroen.ddd.specification.Specifications.not;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.jeroen.ddd.domain.Post;
import org.jeroen.ddd.repository.jpa.JpaRepositoryStrategy;
import org.jeroen.ddd.repository.jpa.SpecificationTranslator;
import org.jeroen.ddd.specification.EqualitySpecification;
import org.jeroen.ddd.specification.Specification;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testContext.xml" })
public class JpaRepositoryStrategyTest {
    private JpaRepositoryStrategy<Post> strategy;

    // Dependencies used inside our strategy
    @PersistenceContext
    private EntityManager em;
    @Resource
    private SpecificationTranslator translator;

    // Entities being used for testing
    private Post post;
    private Post anotherPost;

    @Before
    public void setUp() {
        strategy = new JpaRepositoryStrategy<Post>(Post.class);
        strategy.setEntityManager(em);
        strategy.setTranslator(translator);
        em.persist(post = new Post().setMessage("test"));
        em.persist(anotherPost = new Post().setMessage("another"));
    }

    @Test
    public void testTranslationEqual() {
        Specification<Post> hasTestMessage = new EqualitySpecification<Post>("message", "test");
        List<Post> postList = strategy.matching(hasTestMessage);
        Assert.assertEquals(Arrays.asList(post), postList);
    }

    @Test
    public void testTranslationComposedSpec() {
        Specification<Post> hasTestMessage = new EqualitySpecification<Post>("message", "test");
        List<Post> postList = strategy.matching(not(hasTestMessage));
        Assert.assertEquals(Arrays.asList(anotherPost), postList);
    }

    @Test
    public void testTranslatePredicateSpec() {
        List<Post> postList = strategy.matching(new HasTestMessageJpa());
        Assert.assertEquals(Arrays.asList(post), postList);
    }

    @Test
    public void testTranslatedByConverter() {
        List<Post> postList = strategy.matching(new HasTestMessage());
        Assert.assertEquals(Arrays.asList(post), postList);
    }

}
