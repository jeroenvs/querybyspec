package org.jeroen.ddd.specification.basic;

import org.apache.commons.lang.ObjectUtils;
import org.jeroen.ddd.specification.ValueBoundSpecification;

/**
 * Asserts whether candidates have some exact property value. In order for this specification to be satisfied,
 * the candidate's property value has to be semantically equal to the expected property value. Objects are
 * considered semantically equal, when {@link #equals(Object)} evaluates {@code true}.
 * <p>
 * <code>
 * Specification&lt;Person&gt; namedHenk = new EqualitySpecification&lt;Person&gt;("name","henk"); <br/>
 * namedHenk.isSatisfiedBy(new Person().named("henk")); // Evaluates true <br/>
 * namedHenk.isSatisfiedBy(new Person().named("piet")); // Evaluates false <br/>
 * <code>
 * 
 * @param <T> type of candidates being verified
 * 
 * @author Jeroen van Schagen
 * @since 2-2-2011
 */
public class EqualitySpecification<T> extends ValueBoundSpecification<T> {

    /**
     * Construct a new {@link EqualitySpecification}.
     * @param property expression of the property being checked
     * @param value the value that our property should be equal to
     */
    public EqualitySpecification(String property, Object value) {
        super(property, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isSatisfyingValue(Object candidateValue) {
        return ObjectUtils.equals(candidateValue, getValue());
    }

}
