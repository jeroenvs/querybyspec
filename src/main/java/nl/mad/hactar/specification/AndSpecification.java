package nl.mad.hactar.specification;

public class AndSpecification<T> extends CompositeSpecification<T> {

    public AndSpecification(Specification<T> lhs, Specification<T> rhs) {
        super(lhs, rhs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfiedBy(T candidate) {
        return getLhs().isSatisfiedBy(candidate) && getRhs().isSatisfiedBy(candidate);
    }

}
