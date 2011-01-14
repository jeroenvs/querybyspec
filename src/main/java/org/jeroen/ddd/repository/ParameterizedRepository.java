package org.jeroen.ddd.repository;

import java.util.List;

import org.jeroen.ddd.specification.Specification;

/**
 * Repository that is capable of selecting entities based on a specification parameter.
 * Rather than providing hard typed access methods, the parameterized repository provides
 * a single method, capable of any selection. Whenever hard typed repository methods are
 * desired, the parameterized repository could be delegated to.
 * 
 * @author Jeroen van Schagen
 * @since 6-1-2011
 *
 * @param <T> type of the entities maintained
 */
public interface ParameterizedRepository<T> {

    /**
     * Retrieve all entities, currently stored inside this repository, that statisfy
     * the provided {@link Specification}.
     * 
     * @param specification selection criteria that all returned entities should satisfy
     * @return list of entities that statisfy our provided criteria
     */
    List<T> matching(Specification<T> specification);

}
