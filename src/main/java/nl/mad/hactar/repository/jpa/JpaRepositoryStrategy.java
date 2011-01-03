package nl.mad.hactar.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import nl.mad.hactar.repository.RepositoryStrategy;
import nl.mad.hactar.repository.jpa.translation.SpecificationToPredicateTranslator;
import nl.mad.hactar.specification.Specification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 * Java Persistence API specific implementation of the {@link RepositoryStrategy} interface.
 * Makes internal use of the {@link EntityManager} and converts specifications into predicates.
 * 
 * @author Jeroen van Schagen
 * @since 29-12-2010
 *
 * @param <T> the type of our entities being managed
 */
public class JpaRepositoryStrategy<T> implements RepositoryStrategy<T> {
    private final Class<T> entityClass;
    private EntityManager entityManager;
    private SpecificationToPredicateTranslator translator;

    /**
     * Construct a new {@link JpaRepositoryStrategy}.
     * @param entityClass class of the entities being managed
     */
    public JpaRepositoryStrategy(Class<T> entityClass) {
        super();
        Assert.notNull(entityClass);
        this.entityClass = entityClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> matching(Specification<T> specification) {
        return createQuery(specification).getResultList();
    }

    /**
     * Construct a new typed query, which selects all entities satisfying a specification.
     * @param specification describes what our returned entities should match
     * @return query that is capable of retrieving all matching entities
     */
    private TypedQuery<T> createQuery(Specification<T> specification) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        cq.where(translator.translateToPredicate(specification, root, cq, cb));
        return entityManager.createQuery(cq);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long countBy(Specification<T> specification) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<T> root = cq.from(entityClass);
        cq.select(cb.count(root));
        cq.where(translator.translateToPredicate(specification, root, cq, cb));
        return entityManager.createQuery(cq).getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasAny(Specification<T> specification) {
        return !createQuery(specification).setMaxResults(1).getResultList().isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R extends T> R add(R entity) {
        entityManager.persist(entity);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(T entity) {
        entityManager.remove(entity);
    }

    // Attribute access

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setTranslator(SpecificationToPredicateTranslator translator) {
        this.translator = translator;
    }

}
