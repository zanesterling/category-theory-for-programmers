package com.foo;

import java.util.function.Function;

public class Exercises {
    static class Optional<A> {
        private boolean _isValid;
        private A _value;
        public Optional() { _isValid = false; }
        public Optional(A a) { _isValid = true; _value = a; }
        public boolean isValid() { return _isValid; }
        public A value() { return _value; }
    }

    static Optional<Double> safeRoot(Double x) {
        if (x >= 0) return new Optional(Math.sqrt(x));
        else return new Optional();
    }

    static Optional<Double> safeReciprocal(Double x) {
        if (x != 0) return new Optional(1/x);
        else return new Optional();
    }

    static <A, B, C> Function<A, Optional<C>> compose(Function<B, Optional<C>> f,
                                               Function<A, Optional<B>> g) {
        return (a) -> {
            Optional<B> b = g.apply(a);
            if (!b.isValid()) return new Optional<C>();
            else return f.apply(b.value());
        };
    }

    static <A> void printOptional(String s, Optional<A> a) {
        System.out.print(s);
        if (a.isValid()) System.out.println("Optional(" + a.value() + ")");
        else System.out.println("Optional()");
    }

    public static void main(String[] args) {
        Function<Double, Optional<Double>> safeRootReciprocal = compose(Exercises::safeRoot, Exercises::safeReciprocal);
        printOptional("safeRootReciprocal(2): ", safeRootReciprocal.apply(2.));
        printOptional("safeRootReciprocal(0): ", safeRootReciprocal.apply(0.));
    }
}
