package org.jeroen.ddd.specification;

public class Specifications {

    public static <T> Specification<T> not(Specification<T> specification) {
        return new NotSpecification<T>(specification);
    }

    public static <T> Specification<T> and(Specification<T> lhs, Specification<T> rhs) {
        return new AndSpecification<T>(lhs, rhs);
    }

    public static <T> Specification<T> or(Specification<T> lhs, Specification<T> rhs) {
        return new OrSpecification<T>(lhs, rhs);
    }

}
