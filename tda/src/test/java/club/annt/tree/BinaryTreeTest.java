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

    @BeforeEach
    void setUp() {
        binaryTree0 = new BinaryTree<>();

        binaryTree1 = new BinaryTree<>();
        final NodeBinaryTree<Integer> root = new NodeBinaryTree<>(0);
        binaryTree1.setRoot(root);
        root.setLeft(new BinaryTree<>(new NodeBinaryTree<>(1)));
        root.setRight(new BinaryTree<>(new NodeBinaryTree<>(2)));

    }

    @Test
    void isEmpty() {
        assertTrue(binaryTree0.isEmpty());
        assertFalse(binaryTree1.isEmpty());
    }

    @Test
    void isLeaf() {
        assertTrue(binaryTree1.getRoot().getLeft().isLeaf());
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