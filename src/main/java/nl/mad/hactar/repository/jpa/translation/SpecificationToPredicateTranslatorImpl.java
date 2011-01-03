package nl.mad.hactar.repository.jpa.translation;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import nl.mad.hactar.repository.jpa.PredicateSpecification;
import nl.mad.hactar.specification.Specification;

import org.springframework.core.GenericTypeResolver;

/**
 * Default implementation of {@link SpecificationToPredicateTranslator}, uses an internal map structure
 * to store the conversion logic and is capable of scanning for converters.
 * 
 * @author Jeroen van Schagen
 * @since 28-12-2010
 */
public class SpecificationToPredicateTranslatorImpl implements SpecificationToPredicateTranslator {
    private Map<Class<?>, SpecificationToPredicateConverter<?, ?>> converters = new HashMap<Class<?>, SpecificationToPredicateConverter<?, ?>>();

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> Predicate translateToPredicate(Specification<T> specification, Root<T> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        if (specification instanceof PredicateSpecification<?>) {
            // Predicate aware specifications are capable of resolving their own predicate
            return ((PredicateSpecification<T>) specification).toPredicate(root, cq, cb);
        } else {
            // Plain domain specifications require a converter to retrieve their predicate
            SpecificationToPredicateConverter<Specification<T>, T> converter = findConverter(specification);
            // Whenever no converter was found, we cannot resolve the predicate
            // Inform the developer, by runtime exception, that this converter needs to be registered
            if (converter == null) {
                String message = String.format("Specification [%s] has no registered converters.", specification.getClass().getName());
                throw new IllegalArgumentException(message);
            }
            // Delegate the conversion to our matching converter instance
            return converter.convert(specification, root, cq, cb);
        }
    }

    /**
     * Retrieve the converter, capable of translating our provided specification.
     * 
     * @param <T> type of the entity being used in our specification
     * @param <S> type of specification being converted
     * @param specification the specification for which a matching converter should be found
     * @return a matching converter, if any
     */
    @SuppressWarnings("unchecked")
    private <T, S extends Specification<T>> SpecificationToPredicateConverter<S, T> findConverter(S specification) {
        // TODO: when no translation is found, look for upper classes of the type specification
        return (SpecificationToPredicateConverter<S, T>) converters.get(specification.getClass());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SpecificationToPredicateTranslatorImpl registerConverter(SpecificationToPredicateConverter<?, ?> converter) {
        Class<?> specificationClass = GenericTypeResolver.resolveTypeArguments(converter.getClass(), SpecificationToPredicateConverter.class)[0];
        converters.put(specificationClass, converter);
        return this;
    }

}
