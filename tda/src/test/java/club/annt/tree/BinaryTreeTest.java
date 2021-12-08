package club.annt.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings({"AssertWithoutMessage", "ClassWithoutConstructor",
        "ClassHasNoToStringMethod", "JUnitTestMethodWithNoAssertions",
        "NonBooleanMethodNameMayNotStartWithQuestion",
        "DuplicateStringLiteralInspection", "ClassWithTooManyMethods"})
final class BinaryTreeTest {
    private BinaryTree<Integer> binaryTree0; /* BT vacío */
    private BinaryTree<Integer> binaryTree1; /* BT no-vacío */
    private BinaryTree<Integer> binaryTree2; /* BT no-vacío */
    private BinaryTree<Integer> binaryTree3; /* BT no-vacío */
    private BinaryTree<String>  binaryTree4; /* BT no-vacío */
    private BinaryTree<Integer> binaryTree5; /* BT no-vacío */
    private BinaryTree<Integer> binaryTree6; /* BT no-vacío */

    @SuppressWarnings("OverlyLongMethod")
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
         * BinaryTree #5
         * ****************************************************************** */
        // root
        binaryTree5 = new BinaryTree<>(8);
        // root-izq
        binaryTree5.setLeft(new BinaryTree<>(3));
        // root-der
        binaryTree5.setRight(new BinaryTree<>(10));
        // root-izq-izq
        binaryTree5.getLeft().setLeft(new BinaryTree<>(1));
        // root-izq-der
        binaryTree5.getLeft().setRight(new BinaryTree<>(6));
        // root-izq-der-izq
        binaryTree5.getLeft().getRight().setLeft(new BinaryTree<>(4));
        // root-izq-der-der
        binaryTree5.getLeft().getRight().setRight(new BinaryTree<>(7));
        // root-der-der
        binaryTree5.getRight().setRight(new BinaryTree<>(14));

        /* *********************************************************************
         * BinaryTree #6
         * ****************************************************************** */
        // root
        binaryTree6 = new BinaryTree<>(11);
        // root-izq
        binaryTree6.setLeft(new BinaryTree<>(21));
        // root-der
        binaryTree6.setRight(new BinaryTree<>(2));
        // root-izq-izq
        binaryTree6.getLeft().setLeft(new BinaryTree<>(10));
        // root-izq-izq-izq
        binaryTree6.getLeft().getLeft().setLeft(new BinaryTree<>(14));
    }

    @Test
    void isEmpty() {
        assertTrue(binaryTree0.isEmpty());
        assertFalse(binaryTree1.isEmpty());
        assertFalse(binaryTree2.isEmpty());
        assertFalse(binaryTree3.isEmpty());
        assertFalse(binaryTree4.isEmpty());
        assertFalse(binaryTree5.isEmpty());
        assertFalse(binaryTree6.isEmpty());
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

        /* BinaryTree #5 */
        assertFalse(binaryTree5.isLeaf());
        assertFalse(binaryTree5.getLeft().isLeaf());
        assertTrue(binaryTree5.getRight().getRight().isLeaf());

        /* BinaryTree #6 */
        assertFalse(binaryTree6.isLeaf());
        assertTrue(binaryTree6.getRight().isLeaf());
        assertTrue(binaryTree6.getLeft().getLeft().getLeft().isLeaf());
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

        System.out.println("BinaryTree #5:");
        binaryTree5.recorrerPreOrdenRecursive();

        System.out.println("BinaryTree #6:");
        binaryTree6.recorrerPreOrdenRecursive();
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

        System.out.println("BinaryTree #5:");
        binaryTree5.recorrerEnOrdenRecursive();

        System.out.println("BinaryTree #6:");
        binaryTree6.recorrerEnOrdenRecursive();
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

        System.out.println("BinaryTree #5:");
        binaryTree5.recorrerPostOrdenRecursive();

        System.out.println("BinaryTree #6:");
        binaryTree6.recorrerPostOrdenRecursive();
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

        /* BinaryTree #5 */
        assertEquals(4, binaryTree5.countLeavesRecursive());

        /* BinaryTree #6 */
        assertEquals(2, binaryTree6.countLeavesRecursive());
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

        /* BinaryTree #5 */
        assertEquals(4, binaryTree5.countLeavesIterative());

        /* BinaryTree #6 */
        assertEquals(2, binaryTree6.countLeavesIterative());
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

        /* BinaryTree #5 */
        assertNotNull(binaryTree5.searchRecursive(1, Integer::compareTo));
        assertNull(binaryTree5.searchRecursive(-1, Integer::compareTo));

        /* BinaryTree #6 */
        assertNotNull(binaryTree6.searchRecursive(14, Integer::compareTo));
        assertNull(binaryTree6.searchRecursive(32, Integer::compareTo));
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

        /* BinaryTree #5 */
        assertNotNull(binaryTree5.searchIterative(1, Integer::compareTo));
        assertNull(binaryTree5.searchIterative(-1, Integer::compareTo));

        /* BinaryTree #6 */
        assertNotNull(binaryTree6.searchIterative(14, Integer::compareTo));
        assertNull(binaryTree6.searchIterative(32, Integer::compareTo));
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

        /* BinaryTree #5 */
        assertEquals(1, binaryTree5.getMinRecursive(Integer::compareTo));

        /* BinaryTree #6 */
        assertEquals(2, binaryTree6.getMinRecursive(Integer::compareTo));
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

        /* BinaryTree #5 */
        assertEquals(1, binaryTree5.getMinIterative(Integer::compareTo));

        /* BinaryTree #6 */
        assertEquals(2, binaryTree6.getMinIterative(Integer::compareTo));
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

        /* BinaryTree #5 */
        assertEquals(7, binaryTree5.countDescendantsRecursive());

        /* BinaryTree #6 */
        assertEquals(4, binaryTree6.countDescendantsRecursive());
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

        /* BinaryTree #5 */
        assertEquals(7, binaryTree5.countDescendantsIterative());

        /* BinaryTree #6 */
        assertEquals(4, binaryTree6.countDescendantsIterative());
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

        /* BinaryTree #5 */
        assertEquals(4, binaryTree5.countLevelsRecursive());

        /* BinaryTree #6 */
        assertEquals(4, binaryTree6.countLevelsRecursive());
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

        /* BinaryTree #5 */
        assertEquals(4, binaryTree5.countLevelsIterative());

        /* BinaryTree #6 */
        assertEquals(4, binaryTree6.countLevelsIterative());
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

        /* BinaryTree #6 */
        assertTrue(binaryTree6.isLeftyRecursive());
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

        /* BinaryTree #6 */
        assertTrue(binaryTree6.isLeftyIterative());
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

        /* BinaryTree #5 */
        assertTrue(binaryTree5.isIdenticalRecursive(binaryTree5));
        assertFalse(binaryTree5.isIdenticalRecursive(binaryTree2));

        /* BinaryTree #6 */
        assertTrue(binaryTree6.isIdenticalRecursive(binaryTree6));
        assertFalse(binaryTree6.isIdenticalRecursive(binaryTree5));
    }

    @Test
    void isIdenticalIterative() {
        /* BinaryTree #0 */
        assertTrue(binaryTree0.isIdenticalIterative(binaryTree0,
                                                    Integer::compareTo));

        /* BinaryTree #1 */
        assertTrue(binaryTree1.isIdenticalIterative(binaryTree1,
                                                    Integer::compareTo));

        /* BinaryTree #2 */
        assertTrue(binaryTree2.isIdenticalIterative(binaryTree2,
                                                    Integer::compareTo));
        assertFalse(binaryTree2.isIdenticalIterative(binaryTree1,
                                                     Integer::compareTo));

        /* BinaryTree #3 */
        assertTrue(binaryTree3.isIdenticalIterative(binaryTree3,
                                                    Integer::compareTo));
        assertFalse(binaryTree3.isIdenticalIterative(binaryTree2,
                                                     Integer::compareTo));

        /* BinaryTree #4 */
        assertTrue(binaryTree4.isIdenticalIterative(binaryTree4,
                                                    String::compareTo));

        /* BinaryTree #5 */
        assertTrue(binaryTree5.isIdenticalIterative(binaryTree5,
                                                    Integer::compareTo));
        assertFalse(binaryTree5.isIdenticalIterative(binaryTree2,
                                                     Integer::compareTo));

        /* BinaryTree #6 */
        assertTrue(binaryTree6.isIdenticalIterative(binaryTree6,
                                                    Integer::compareTo));
        assertFalse(binaryTree6.isIdenticalIterative(binaryTree5,
                                                     Integer::compareTo));
    }

    @Test
    void isHeightBalancedRecursive() {
        /* BinaryTree #0 */
        assertTrue(binaryTree0.isHeightBalancedRecursive());

        /* BinaryTree #1 */
        assertTrue(binaryTree1.isHeightBalancedRecursive());

        /* BinaryTree #2 */
        assertTrue(binaryTree2.isHeightBalancedRecursive());

        /* BinaryTree #3 */
        assertTrue(binaryTree3.isHeightBalancedRecursive());

        /* BinaryTree #4 */
        assertTrue(binaryTree4.isHeightBalancedRecursive());

        /* BinaryTree #5 */
        assertTrue(binaryTree5.isHeightBalancedRecursive());

        /* BinaryTree #6 */
        assertFalse(binaryTree6.isHeightBalancedRecursive());
    }

    @Test
    void isHeightBalancedIterative() {
        /* BinaryTree #0 */
        assertTrue(binaryTree0.isHeightBalancedIterative());

        /* BinaryTree #1 */
        assertTrue(binaryTree1.isHeightBalancedIterative());

        /* BinaryTree #2 */
        assertTrue(binaryTree2.isHeightBalancedIterative());

        /* BinaryTree #3 */
        assertTrue(binaryTree3.isHeightBalancedIterative());

        /* BinaryTree #4 */
        assertTrue(binaryTree4.isHeightBalancedIterative());

        /* BinaryTree #5 */
        assertTrue(binaryTree5.isHeightBalancedIterative());

        /* BinaryTree #6 */
        assertFalse(binaryTree6.isHeightBalancedIterative());
    }

    @Test
    void countNodesWithOnlyChildRecursive() {
        /* BinaryTree #0 */
        assertEquals(0, binaryTree0.countNodesWithOnlyChildRecursive());

        /* BinaryTree #1 */
        assertEquals(0, binaryTree1.countNodesWithOnlyChildRecursive());

        /* BinaryTree #2 */
        assertEquals(0, binaryTree2.countNodesWithOnlyChildRecursive());

        /* BinaryTree #3 */
        assertEquals(1, binaryTree3.countNodesWithOnlyChildRecursive());

        /* BinaryTree #4 */
        assertEquals(1, binaryTree4.countNodesWithOnlyChildRecursive());

        /* BinaryTree #5 */
        assertEquals(1, binaryTree5.countNodesWithOnlyChildRecursive());

        /* BinaryTree #6 */
        assertEquals(2, binaryTree6.countNodesWithOnlyChildRecursive());
    }

    @Test
    void countNodesWithOnlyChildIterative() {
        /* BinaryTree #0 */
        assertEquals(0, binaryTree0.countNodesWithOnlyChildIterative());

        /* BinaryTree #1 */
        assertEquals(0, binaryTree1.countNodesWithOnlyChildIterative());

        /* BinaryTree #2 */
        assertEquals(0, binaryTree2.countNodesWithOnlyChildIterative());

        /* BinaryTree #3 */
        assertEquals(1, binaryTree3.countNodesWithOnlyChildIterative());

        /* BinaryTree #4 */
        assertEquals(1, binaryTree4.countNodesWithOnlyChildIterative());

        /* BinaryTree #5 */
        assertEquals(1, binaryTree5.countNodesWithOnlyChildIterative());

        /* BinaryTree #6 */
        assertEquals(2, binaryTree6.countNodesWithOnlyChildIterative());
    }

    @Test
    void largestValueOfEachLevelRecursive() {
        System.out.println("BinaryTree #1:");
        binaryTree1.largestValueOfEachLevelRecursive(
                (bt1, bt2) -> bt2.getData() - bt1.getData());

        System.out.println("BinaryTree #2:");
        binaryTree2.largestValueOfEachLevelRecursive(
                (bt1, bt2) -> bt2.getData() - bt1.getData());

        System.out.println("BinaryTree #3:");
        binaryTree3.largestValueOfEachLevelRecursive(
                (bt1, bt2) -> bt2.getData() - bt1.getData());

        System.out.println("BinaryTree #4:");
        binaryTree4.largestValueOfEachLevelRecursive(
                Comparator.comparing(BinaryTree::getData));

        System.out.println("BinaryTree #5:");
        binaryTree5.largestValueOfEachLevelRecursive(
                (bt1, bt2) -> bt2.getData() - bt1.getData());

        System.out.println("BinaryTree #6:");
        binaryTree6.largestValueOfEachLevelRecursive(
                (bt1, bt2) -> bt2.getData() - bt1.getData());
    }

    @Test
    void largestValueOfEachLevelIterative() {
        System.out.println("BinaryTree #1:");
        binaryTree1.largestValueOfEachLevelIterative(
                (bt1, bt2) -> bt2.getData() - bt1.getData());

        System.out.println("BinaryTree #2:");
        binaryTree2.largestValueOfEachLevelIterative(
                (bt1, bt2) -> bt2.getData() - bt1.getData());

        System.out.println("BinaryTree #3:");
        binaryTree3.largestValueOfEachLevelIterative(
                (bt1, bt2) -> bt2.getData() - bt1.getData());

        System.out.println("BinaryTree #4:");
        binaryTree4.largestValueOfEachLevelIterative(
                Comparator.comparing(BinaryTree::getData));

        System.out.println("BinaryTree #5:");
        binaryTree5.largestValueOfEachLevelIterative(
                (bt1, bt2) -> bt2.getData() - bt1.getData());

        System.out.println("BinaryTree #6:");
        binaryTree6.largestValueOfEachLevelIterative(
                (bt1, bt2) -> bt2.getData() - bt1.getData());
    }
}