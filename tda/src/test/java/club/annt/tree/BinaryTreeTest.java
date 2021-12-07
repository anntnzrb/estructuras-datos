package club.annt.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings({"AssertWithoutMessage", "ClassWithoutConstructor",
        "ClassHasNoToStringMethod", "JUnitTestMethodWithNoAssertions",
        "NonBooleanMethodNameMayNotStartWithQuestion"})
final class BinaryTreeTest {
    private BinaryTree<Integer> binaryTree0; /* BT vacío */
    private BinaryTree<Integer> binaryTree1; /* BT no-vacío */
    private BinaryTree<Integer> binaryTree2; /* BT no-vacío */
    private BinaryTree<Integer> binaryTree3; /* BT no-vacío */
    private BinaryTree<String>  binaryTree4; /* BT no-vacío */
    private BinaryTree<Integer>  binaryTree5; /* BT no-vacío */

    @BeforeEach
    void setUp() {
        /* *********************************************************************
         * BinaryTree #0
         ******************************************************************** */
        binaryTree0 = new BinaryTree<>();

        /* *********************************************************************
         * BinaryTree #1
         * ****************************************************************** */
        binaryTree1 = new BinaryTree<>(0);

        /* *********************************************************************
         * BinaryTree #2
         * ****************************************************************** */
        binaryTree2 = new BinaryTree<>(0);
        binaryTree2.setLeft(new BinaryTree<>(1));
        binaryTree2.setRight(new BinaryTree<>(2));

        /* *********************************************************************
         * BinaryTree #3
         * ****************************************************************** */
        // root
        binaryTree3 = new BinaryTree<>(0);
        // root-izq
        binaryTree3.setLeft(new BinaryTree<>(1));
        // root-der
        binaryTree3.setRight(new BinaryTree<>(2));
        // root-izq-izq
        binaryTree3.getLeft().setLeft(new BinaryTree<>(3));
        // root-izq-der
        binaryTree3.getLeft().setRight(new BinaryTree<>(4));
        // root-der-izq
        binaryTree3.getRight().setLeft(new BinaryTree<>(5));
        // root-der-der
        binaryTree3.getRight().setRight(new BinaryTree<>(6));
        // root-der-der-der
        binaryTree3.getRight().getRight().setRight(new BinaryTree<>(7));

        /* *********************************************************************
         * BinaryTree #4
         * ****************************************************************** */
        // root
        binaryTree4 = new BinaryTree<>("Zero");
        // root-izq
        binaryTree4.setLeft(new BinaryTree<>("One"));
        // root-der
        binaryTree4.setRight(new BinaryTree<>("Two"));
        // root-izq-izq
        binaryTree4.getLeft().setLeft(new BinaryTree<>("Three"));
        // root-izq-der
        binaryTree4.getLeft().setRight(new BinaryTree<>("Four"));
        // root-der-der
        binaryTree4.getRight().setRight(new BinaryTree<>("Five"));

        /* *********************************************************************
         * BinaryTree #3
         * ****************************************************************** */
        // root
        binaryTree5 = new BinaryTree<>(8);
        // root-izq
        binaryTree5.setLeft(new BinaryTree<>(3));
        // root-izq-izq
        binaryTree5.getLeft().setLeft(new BinaryTree<>(1));
        // root-izq-der
        binaryTree5.getLeft().setRight(new BinaryTree<>(6));
        // root-izq-der-izq
        binaryTree5.getLeft().getRight().setLeft(new BinaryTree<>(4));
        // root-izq-der-der
        binaryTree5.getLeft().getRight().setRight(new BinaryTree<>(7));
        // root-der
        binaryTree5.setRight(new BinaryTree<>(10));
        // root-der-der
        binaryTree5.getRight().setRight(new BinaryTree<>(14));
    }

    @Test
    void isEmpty() {
        assertTrue(binaryTree0.isEmpty());
        assertFalse(binaryTree1.isEmpty());
        assertFalse(binaryTree2.isEmpty());
        assertFalse(binaryTree3.isEmpty());
        assertFalse(binaryTree4.isEmpty());
    }

    @Test
    void isLeaf() {
        /* BinaryTree #0 */
        assertFalse(binaryTree0.isLeaf());

        /* BinaryTree #1 */
        assertTrue(binaryTree1.isLeaf());

        /* BinaryTree #2 */
        assertTrue(binaryTree2.getLeft().isLeaf());
        assertTrue(binaryTree2.getRight().isLeaf());

        /* BinaryTree #3 */
        assertFalse(binaryTree3.getLeft().isLeaf());
        assertTrue(binaryTree3.getLeft().getLeft().isLeaf());
        assertTrue(binaryTree3.getRight().getRight().getRight().isLeaf());

        /* BinaryTree #4 */
        assertFalse(binaryTree4.getLeft().isLeaf());
        assertTrue(binaryTree4.getLeft().getLeft().isLeaf());
    }

    @Test
    void recorrerPreOrdenRecursive() {
        System.out.println("BinaryTree #1:");
        binaryTree1.recorrerPreOrdenRecursive();

        System.out.println("BinaryTree #2:");
        binaryTree2.recorrerPreOrdenRecursive();

        System.out.println("BinaryTree #3:");
        binaryTree3.recorrerPreOrdenRecursive();

        System.out.println("BinaryTree #4:");
        binaryTree4.recorrerPreOrdenRecursive();
    }

    @Test
    void recorrerEnOrdenRecursive() {
        System.out.println("BinaryTree #1:");
        binaryTree1.recorrerEnOrdenRecursive();

        System.out.println("BinaryTree #2:");
        binaryTree2.recorrerEnOrdenRecursive();

        System.out.println("BinaryTree #3:");
        binaryTree3.recorrerEnOrdenRecursive();

        System.out.println("BinaryTree #4:");
        binaryTree4.recorrerEnOrdenRecursive();
    }

    @Test
    void recorrerPostOrdenRecursive() {
        System.out.println("BinaryTree #1:");
        binaryTree1.recorrerPostOrdenRecursive();

        System.out.println("BinaryTree #2:");
        binaryTree2.recorrerPostOrdenRecursive();

        System.out.println("BinaryTree #3:");
        binaryTree3.recorrerPostOrdenRecursive();

        System.out.println("BinaryTree #4:");
        binaryTree4.recorrerPostOrdenRecursive();
    }

    @Test
    void countLeavesRecursive() {
        /* BinaryTree #0 */
        assertEquals(0, binaryTree0.countLeavesRecursive());

        /* BinaryTree #1 */
        assertEquals(1, binaryTree1.countLeavesRecursive());

        /* BinaryTree #2 */
        assertEquals(2, binaryTree2.countLeavesRecursive());

        /* BinaryTree #3 */
        assertEquals(4, binaryTree3.countLeavesRecursive());

        /* BinaryTree #4 */
        assertEquals(3, binaryTree4.countLeavesRecursive());
    }

    @Test
    void countLeavesIterative() {
        /* BinaryTree #0 */
        assertEquals(0, binaryTree0.countLeavesIterative());

        /* BinaryTree #1 */
        assertEquals(1, binaryTree1.countLeavesIterative());

        /* BinaryTree #2 */
        assertEquals(2, binaryTree2.countLeavesIterative());

        /* BinaryTree #3 */
        assertEquals(4, binaryTree3.countLeavesIterative());

        /* BinaryTree #4 */
        assertEquals(3, binaryTree4.countLeavesIterative());
    }

    @Test
    void searchRecursive() {
        /* BinaryTree #0 */
        assertNull(binaryTree0.searchRecursive(0, Integer::compareTo));

        /* BinaryTree #1 */
        assertNotNull(binaryTree1.searchRecursive(0, Integer::compareTo));

        /* BinaryTree #2 */
        assertNotNull(binaryTree2.searchRecursive(0, Integer::compareTo));
        assertNull(binaryTree2.searchRecursive(3, Integer::compareTo));

        /* BinaryTree #3 */
        assertNotNull(binaryTree3.searchRecursive(7, Integer::compareTo));
        assertNull(binaryTree3.searchRecursive(8, Integer::compareTo));

        /* BinaryTree #4 */
        assertNotNull(binaryTree4.searchRecursive("One",
                                                  Comparator.comparingInt(String::length)));
        assertNull(binaryTree4.searchRecursive("TooLong",
                                               Comparator.comparingInt(String::length)));
    }

    @Test
    void searchIterative() {
        /* BinaryTree #0 */
        assertNull(binaryTree0.searchIterative(0, Integer::compareTo));

        /* BinaryTree #1 */
        assertNotNull(binaryTree1.searchIterative(0, Integer::compareTo));

        /* BinaryTree #2 */
        assertNotNull(binaryTree2.searchIterative(0, Integer::compareTo));
        assertNull(binaryTree2.searchIterative(3, Integer::compareTo));

        /* BinaryTree #3 */
        assertNotNull(binaryTree3.searchIterative(7, Integer::compareTo));
        assertNull(binaryTree3.searchIterative(8, Integer::compareTo));

        /* BinaryTree #4 */
        assertNotNull(binaryTree4.searchIterative("One",
                                                  Comparator.comparingInt(String::length)));
        assertNull(binaryTree4.searchIterative("TooLong",
                                               Comparator.comparingInt(String::length)));
    }

    @Test
    void getMinRecursive() {
        /* BinaryTree #1 */
        assertEquals(0, binaryTree1.getMinRecursive(Integer::compareTo));

        /* BinaryTree #2 */
        assertEquals(0, binaryTree2.getMinRecursive(Integer::compareTo));

        /* BinaryTree #3 */
        assertEquals(0, binaryTree3.getMinRecursive(Integer::compareTo));

        /* BinaryTree #4 */
        assertEquals("Five", binaryTree4.getMinRecursive(String::compareTo));
    }

    @Test
    void getMinIterative() {
        /* BinaryTree #1 */
        assertEquals(0, binaryTree1.getMinIterative(Integer::compareTo));

        /* BinaryTree #2 */
        assertEquals(0, binaryTree2.getMinIterative(Integer::compareTo));

        /* BinaryTree #3 */
        assertEquals(0, binaryTree3.getMinIterative(Integer::compareTo));

        /* BinaryTree #4 */
        assertEquals("Five", binaryTree4.getMinIterative(String::compareTo));
    }

    @Test
    void countDescendantsRecursive() {
        /* BinaryTree #1 */
        assertEquals(0, binaryTree1.countDescendantsRecursive());

        /* BinaryTree #2 */
        assertEquals(2, binaryTree2.countDescendantsRecursive());

        /* BinaryTree #3 */
        assertEquals(7, binaryTree3.countDescendantsRecursive());

        /* BinaryTree #4 */
        assertEquals(5, binaryTree4.countDescendantsRecursive());
    }

    @Test
    void countDescendantsIterative() {
        /* BinaryTree #1 */
        assertEquals(0, binaryTree1.countDescendantsIterative());

        /* BinaryTree #2 */
        assertEquals(2, binaryTree2.countDescendantsIterative());

        /* BinaryTree #3 */
        assertEquals(7, binaryTree3.countDescendantsIterative());

        /* BinaryTree #4 */
        assertEquals(5, binaryTree4.countDescendantsIterative());
    }

    @Test
    void countLevelsRecursive() {
        /* BinaryTree #0 */
        assertEquals(0, binaryTree0.countLevelsRecursive());

        /* BinaryTree #1 */
        assertEquals(1, binaryTree1.countLevelsRecursive());

        /* BinaryTree #2 */
        assertEquals(2, binaryTree2.countLevelsRecursive());

        /* BinaryTree #3 */
        assertEquals(4, binaryTree3.countLevelsRecursive());

        /* BinaryTree #4 */
        assertEquals(3, binaryTree4.countLevelsRecursive());
    }

    @Test
    void countLevelsIterative() {
        /* BinaryTree #0 */
        assertEquals(0, binaryTree0.countLevelsIterative());

        /* BinaryTree #1 */
        assertEquals(1, binaryTree1.countLevelsIterative());

        /* BinaryTree #2 */
        assertEquals(2, binaryTree2.countLevelsIterative());

        /* BinaryTree #3 */
        assertEquals(4, binaryTree3.countLevelsIterative());

        /* BinaryTree #4 */
        assertEquals(3, binaryTree4.countLevelsIterative());
    }

    @Test
    void isLeftyRecursive() {
        /* BinaryTree #0 */
        assertTrue(binaryTree0.isLeftyRecursive());

        /* BinaryTree #1 */
        assertTrue(binaryTree1.isLeftyRecursive());

        /* BinaryTree #2 */
        assertFalse(binaryTree2.isLeftyRecursive());

        /* BinaryTree #3 */
        assertFalse(binaryTree3.isLeftyRecursive());

        /* BinaryTree #4 */
        assertFalse(binaryTree4.isLeftyRecursive());

        /* BinaryTree #5 */
        assertFalse(binaryTree5.isLeftyRecursive());
    }

    @Test
    void isLeftyIterative() {
        /* BinaryTree #0 */
        assertTrue(binaryTree0.isLeftyIterative());

        /* BinaryTree #1 */
        assertTrue(binaryTree1.isLeftyIterative());

        /* BinaryTree #2 */
        assertFalse(binaryTree2.isLeftyIterative());

        /* BinaryTree #3 */
        assertFalse(binaryTree3.isLeftyIterative());

        /* BinaryTree #4 */
        assertFalse(binaryTree4.isLeftyIterative());

        /* BinaryTree #5 */
        assertFalse(binaryTree5.isLeftyIterative());
    }

    @Test
    void isIdenticalRecursive() {
        /* BinaryTree #0 */
        assertTrue(binaryTree0.isIdenticalRecursive(binaryTree0));

        /* BinaryTree #1 */
        assertTrue(binaryTree1.isIdenticalRecursive(binaryTree1));

        /* BinaryTree #2 */
        assertTrue(binaryTree2.isIdenticalRecursive(binaryTree2));
        assertFalse(binaryTree2.isIdenticalRecursive(binaryTree1));

        /* BinaryTree #3 */
        assertTrue(binaryTree3.isIdenticalRecursive(binaryTree3));
        assertFalse(binaryTree3.isIdenticalRecursive(binaryTree2));

        /* BinaryTree #4 */
        assertTrue(binaryTree4.isIdenticalRecursive(binaryTree4));
    }

    @Test
    void isIdenticalIterative() {
        /* BinaryTree #0 */
        assertTrue(binaryTree0.isIdenticalIterative(binaryTree0));

        /* BinaryTree #1 */
        assertTrue(binaryTree1.isIdenticalIterative(binaryTree1));

        /* BinaryTree #2 */
        assertTrue(binaryTree2.isIdenticalIterative(binaryTree2));
        assertFalse(binaryTree2.isIdenticalIterative(binaryTree1));

        /* BinaryTree #3 */
        assertTrue(binaryTree3.isIdenticalIterative(binaryTree3));
        assertFalse(binaryTree3.isIdenticalIterative(binaryTree2));

        /* BinaryTree #4 */
        assertTrue(binaryTree4.isIdenticalIterative(binaryTree4));
    }
}