package nl.mad.hactar.specification;

public class OrSpecification<T> extends CompositeSpecification<T> {

    public OrSpecification(Specification<T> lhs, Specification<T> rhs) {
        super(lhs, rhs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfiedBy(T candidate) {
        return getLhs().isSatisfiedBy(candidate) || getRhs().isSatisfiedBy(candidate);
    }

}
