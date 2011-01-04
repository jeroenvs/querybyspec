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
 * Default implementation of {@link SpecificationTranslator}, uses an internal map structure
 * to store the conversion logic and is capable of scanning for converters.
 * 
 * @author Jeroen van Schagen
 * @since 28-12-2010
 */
public class SpecificationTranslatorImpl implements SpecificationTranslator {
    private Map<Class<?>, SpecificationConverter<?, ?>> converters = new HashMap<Class<?>, SpecificationConverter<?, ?>>();

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> Predicate translateToPredicate(Specification<T> spec, Root<T> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        if (spec instanceof JpaSpecification<?>) {
            // Predicate aware specifications are capable of resolving their own predicate
            return ((JpaSpecification<T>) spec).toPredicate(root, cq, cb);
        } else {
            // Domain oriented specifications require a converter to resolve their predicate
            SpecificationConverter<Specification<T>, T> converter = findConverter(spec);
            // Whenever no converter was found, the predicate cannot be resolved
            if (converter == null) {
                String message = String.format("Specification [%s] has no registered converters.", spec.getClass().getName());
                throw new IllegalArgumentException(message);
            }
            // Delegate the conversion to our matching converter instance
            return converter.convert(spec, root, cq, cb);
        }
    }

    /**
     * Retrieve the converter, capable of translating our provided specification.
     * @param <T> type of the entity being used in our specification
     * @param <S> type of specification being converted
     * @param spec the specification for which a matching converter should be found
     * @return a matching converter, if any
     */
    @SuppressWarnings("unchecked")
    private <T, S extends Specification<T>> SpecificationConverter<S, T> findConverter(S spec) {
        // TODO: when no translation is found, look for upper classes of the type specification
        return (SpecificationConverter<S, T>) converters.get(spec.getClass());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SpecificationTranslatorImpl registerConverter(SpecificationConverter<?, ?> converter) {
        Class<?> specificationClass = GenericTypeResolver.resolveTypeArguments(converter.getClass(), SpecificationConverter.class)[0];
        converters.put(specificationClass, converter);
        return this;
    }

}
