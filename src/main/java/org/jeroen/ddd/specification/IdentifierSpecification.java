package org.jeroen.ddd.specification;

public class IdentifierSpecification<T> extends EqualitySpecification<T> {
    private static final String IDENTIFIER_EXPRESSION = "id";

    public IdentifierSpecification(Object value) {
        super(IDENTIFIER_EXPRESSION, value);
    }

}