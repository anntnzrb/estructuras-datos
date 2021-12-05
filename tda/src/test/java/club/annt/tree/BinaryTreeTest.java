package club.annt.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings({"AssertWithoutMessage", "ClassWithoutConstructor",
        "ClassHasNoToStringMethod", "JUnitTestMethodWithNoAssertions",
        "NonBooleanMethodNameMayNotStartWithQuestion"})
final class BinaryTreeTest {
    private BinaryTree<Integer> binaryTree0; /* BT vacío */
    private BinaryTree<Integer> binaryTree1; /* BT no-vacío */
    private BinaryTree<Integer> binaryTree2; /* BT no-vacío */

    @BeforeEach
    void setUp() {
        /* *********************************************************************
         * BinaryTree #0
         ******************************************************************** */
        binaryTree0 = new BinaryTree<>();

        /* *********************************************************************
         * BinaryTree #1
         ******************************************************************** */
        binaryTree1 = new BinaryTree<>(0);
        binaryTree1.setLeft(new BinaryTree<>(1));
        binaryTree1.setRight(new BinaryTree<>(2));

        /* *********************************************************************
         * BinaryTree #2
         ******************************************************************** */
        // root
        binaryTree2 = new BinaryTree<>(0);
        // root-izq
        binaryTree2.setLeft(new BinaryTree<>(1));
        // root-der
        binaryTree2.setRight(new BinaryTree<>(2));
        // root-izq-izq
        binaryTree2.getLeft().setLeft(new BinaryTree<>(3));
        // root-izq-der
        binaryTree2.getLeft().setRight(new BinaryTree<>(4));
        // root-der-izq
        binaryTree2.getRight().setLeft(new BinaryTree<>(5));
        // root-der-der
        binaryTree2.getRight().setRight(new BinaryTree<>(6));
        // root-der-der-der
        binaryTree2.getRight().getRight().setRight(new BinaryTree<>(7));
    }

    @Test
    void isEmpty() {
        assertTrue(binaryTree0.isEmpty());
        assertFalse(binaryTree1.isEmpty());
        assertFalse(binaryTree2.isEmpty());
    }

    @Test
    void isLeaf() {
        // TODO
    }

    @Test
    void recorrerPreOrdenRecursive() {
        binaryTree1.recorrerPostOrdenRecursive();
    }

    @Test
    void recorrerEnOrdenRecursive() {
        binaryTree1.recorrerEnOrdenRecursive();
    }

    @Test
    void recorrerPostOrdenRecursive() {
        binaryTree1.recorrerPostOrdenRecursive();
    }

    @Test
    void countLeavesRecursive() {
        // TODO
    }

    @Test
    void countLeavesIterative() {
        // TODO
    }
}