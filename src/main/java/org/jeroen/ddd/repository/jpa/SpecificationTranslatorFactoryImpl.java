package org.jeroen.ddd.repository.jpa;

import java.io.IOException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

/**
 * Default implementation of {@link SpecificationTranslatorFactory}.
 * 
 * @author Jeroen van Schagen
 * @since 30-12-2010
 */
public class SpecificationTranslatorFactoryImpl implements SpecificationTranslatorFactory, ApplicationContextAware {
    private ApplicationContext applicationContext;

    /**
     * {@inheritDoc}
     */
    @Override
    public SpecificationTranslator withDefaultConverters() {
        SpecificationTranslator translator = new SpecificationTranslatorImpl();
        translator.registerConverter(new EqualConverter());
        translator.registerConverter(new NotConverter(translator));
        translator.registerConverter(new AndConverter(translator));
        translator.registerConverter(new OrConverter(translator));
        return translator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SpecificationTranslator withAnnotatedConverters(String basePackage) {
        return registerAnnotatedConverters(withDefaultConverters(), basePackage);
    }

    /**
     * Register all annotated converters in the provided base package.
     * 
     * @param translator recieves all scanned converter instances
     * @param basePackage the base package of our custom converters
     * @return same translator instance, used for chaining
     */
    private SpecificationTranslator registerAnnotatedConverters(SpecificationTranslator translator, String basePackage) {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new IsRegisterableConverterFilter());
        for (BeanDefinition bd : scanner.findCandidateComponents(basePackage)) {
            try {
                Class<?> converterClass = Class.forName(bd.getBeanClassName());
                translator.registerConverter(getConverterOfType(converterClass));
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException(e);
            }
        }
        return translator;
    }

    /**
     * Retrieve converter from application context, or else construct a {@code new} instance.
     * 
     * @param converterClass the type of our converter
     * @return a new or existing converter instance
     */
    private SpecificationConverter<?, ?> getConverterOfType(Class<?> converterClass) {
        Object instance = null;
        try {
            instance = applicationContext.getBean(converterClass);
        } catch (NoSuchBeanDefinitionException e) {
            instance = BeanUtils.instantiate(converterClass);
        }
        return (SpecificationConverter<?, ?>) instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    // Matches annotated specification-to-predicate converters
    private static class IsRegisterableConverterFilter implements TypeFilter {
        private final TypeFilter converterTypeFilter = new AssignableTypeFilter(SpecificationConverter.class);
        private final TypeFilter registeredAnnotationFilter = new AnnotationTypeFilter(Registered.class);

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
            boolean isConverterType = converterTypeFilter.match(metadataReader, metadataReaderFactory);
            boolean isAnnotatedAsRegistered = registeredAnnotationFilter.match(metadataReader, metadataReaderFactory);
            return isConverterType && isAnnotatedAsRegistered;
        }
    }

}
