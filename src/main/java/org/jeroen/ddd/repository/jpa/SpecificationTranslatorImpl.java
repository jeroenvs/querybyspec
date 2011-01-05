package org.jeroen.ddd.repository.jpa;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.jeroen.ddd.specification.Specification;
import org.springframework.core.GenericTypeResolver;

/**
 * Default {@link SpecificationTranslator} implementation which registers and applies
 * specification converters based on a map data structure.
 * 
 * @author Jeroen van Schagen
 * @since 28-12-2010
 */
public class SpecificationTranslatorImpl implements SpecificationTranslator {
    private Map<Class<?>, SpecificationConverter<?, ?>> converterMapping = new HashMap<Class<?>, SpecificationConverter<?, ?>>();

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> Predicate translateToPredicate(Specification<T> specification, Root<T> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        if (specification instanceof JpaSpecification<?>) {
            // Predicate aware specifications are capable of resolving their own predicate
            return ((JpaSpecification<T>) specification).toPredicate(root, cq, cb);
        } else {
            // Domain oriented specifications require a converter to resolve their predicate
            SpecificationConverter<Specification<T>, T> converter = findConverter(specification);
            // Whenever no converter was found, the predicate cannot be resolved
            if (converter == null) {
                String message = String.format("Specification [%s] has no registered converters.", specification.getClass().getName());
                throw new IllegalArgumentException(message);
            }
            // Delegate the conversion to our matching converter instance
            return converter.convert(specification, root, cq, cb);
        }
    }

    /**
     * Retrieve the converter, capable of translating our provided specification. Whenever no converter could
     * be found for the provided specification, we look for a converter on the specification's super class.
     * 
     * @param <T> type of the entity being used in our specification
     * @param <S> type of specification being converted
     * @param specification the specification for which a matching converter should be found
     * @return a matching converter, if any
     */
    @SuppressWarnings("unchecked")
    private <T, S extends Specification<T>> SpecificationConverter<S, T> findConverter(S specification) {
        Class<?> specificationClass = specification.getClass();
        Object converter = null;
        do {
            converter = converterMapping.get(specificationClass);
        } while (converter == null && (specificationClass = specificationClass.getSuperclass()) != null);
        return (SpecificationConverter<S, T>) converter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SpecificationTranslator registerConverter(SpecificationConverter<?, ?> converter) {
        Class<?> specificationClass = GenericTypeResolver.resolveTypeArguments(converter.getClass(), SpecificationConverter.class)[0];
        converterMapping.put(specificationClass, converter);
        return this; // Return this instance to enable chaining
    }

}
