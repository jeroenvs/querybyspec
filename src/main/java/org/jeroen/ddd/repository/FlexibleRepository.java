package org.jeroen.ddd.repository;

import java.util.List;

import org.jeroen.ddd.specification.Specification;

/**
 * Repository that is capable of finding entities based on a specification parameter.
 * 
 * @author Jeroen van Schagen
 * @since 6-1-2011
 *
 * @param <T> type of the entities maintained
 */
public interface FlexibleRepository<T> {

    /**
     * Retrieve all entities, stored inside this repository, that match
     * the provided {@link Specification}.
     * 
     * @param specification criteria that all matching entities should satisfy
     * @return list of matching entities
     */
    List<T> matching(Specification<T> specification);

}
