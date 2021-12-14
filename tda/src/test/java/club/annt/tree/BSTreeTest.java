package club.annt.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"ClassWithoutConstructor", "ClassHasNoToStringMethod"})
final class BSTreeTest {
    private BSTree<Integer, Integer> bsTree0;

    @BeforeEach
    void setUp() {
        bsTree0 = new BSTree<>(0, 8);
        bsTree0.insertRecursive(1, 4);
        bsTree0.insertRecursive(2, 2);
        bsTree0.insertRecursive(3, 8);
        bsTree0.insertRecursive(4, 12);
        bsTree0.insertRecursive(5, -1);
    }

    @Test
    void insert() {
        System.out.println(bsTree0.getKey());
        System.out.println(bsTree0.getLeft().getKey());
        System.out.println(bsTree0.getRight().getKey());
    }

    @Test
    void getMin() {
        System.out.println(bsTree0.getMin());
    }
}