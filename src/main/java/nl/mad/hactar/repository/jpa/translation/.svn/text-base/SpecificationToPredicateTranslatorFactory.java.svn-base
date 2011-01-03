package nl.mad.hactar.repository.jpa.translation;

/**
 * Provides construction logic for {@link SpecificationToPredicateTranslator} instances.
 * The construction of a translator can be quite difficult, as the various converters have
 * to be registered. By providing a factory, users can remain unaware of this construction.
 * 
 * @author Jeroen van Schagen
 * @since 30-12-2010
 */
public interface SpecificationToPredicateTranslatorFactory {

    /**
     * Construct a new {@link SpecificationToPredicateTranslator} with all default converters.
     * @return translator instance with all default converters
     */
    SpecificationToPredicateTranslator createTranslator();

    /**
     * Construct a new {@link SpecificationToPredicateTranslator} with all default converters,
     * and the @RegisteredAutomatically annotated custom converters in a certain base package.
     * 
     * @param basePackage the base package of our custom converters
     * @return translator instance with all default and annotated converters
     */
    SpecificationToPredicateTranslator createTranslator(String basePackage);

}
