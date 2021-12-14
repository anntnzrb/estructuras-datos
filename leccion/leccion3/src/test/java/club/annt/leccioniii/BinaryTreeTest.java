package club.annt.leccioniii;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryTreeTest {
    private BinaryTree<String>  binaryTree0;
    private BinaryTree<Integer> binaryTree1;
    private BinaryTree<Integer> binaryTree2;
    private BinaryTree<Integer> intersectBT;

    @BeforeEach
    void setUp() {
        /* *********************************************************************
         * BinaryTree #0 === BinaryTree Ej #1
         ******************************************************************** */
        // root
        binaryTree0 = new BinaryTree<>("Two");
        // root-izq
        binaryTree0.setLeft(new BinaryTree<>("Seven"));
        // root-der
        binaryTree0.setRight(new BinaryTree<>("Five"));
        // root-izq-der
        binaryTree0.getLeft().setRight(new BinaryTree<>("Six"));
        // root-der-der
        binaryTree0.getRight().setRight(new BinaryTree<>("Nine"));
        // root-izq-der-izq
        binaryTree0.getLeft().getRight().setLeft(new BinaryTree<>("One"));
        // root-izq-der-der
        binaryTree0.getLeft().getRight().setRight(new BinaryTree<>("Three"));
        // root-der-der-izq
        binaryTree0.getRight().getRight().setLeft(new BinaryTree<>("Four"));

        /* *********************************************************************
         * BinaryTree #1, #2, intersect === Árboles a encontrar intersección
         ******************************************************************** */
        // root
        binaryTree1 = new BinaryTree<>(1);
        // root-izq
        binaryTree1.setLeft(new BinaryTree<>(3));
        // root-der
        binaryTree1.setRight(new BinaryTree<>(5));
        // root-izq-izq
        binaryTree1.getLeft().setLeft(new BinaryTree<>(2));
        // root-der-izq
        binaryTree1.getRight().setLeft(new BinaryTree<>(1));
        // root-der-der
        binaryTree1.getRight().setRight(new BinaryTree<>(4));
        // root-der-izq-izq
        binaryTree1.getRight().getLeft().setLeft(new BinaryTree<>(1));
        // root-der-izq-der
        binaryTree1.getRight().getLeft().setRight(new BinaryTree<>(0));
        // ====================================================================
        // root
        binaryTree2 = new BinaryTree<>(2);
        // root-izq
        binaryTree2.setLeft(new BinaryTree<>(1));
        // root-der
        binaryTree2.setRight(new BinaryTree<>(4));
        // root-izq-izq
        binaryTree2.getLeft().setLeft(new BinaryTree<>(3));
        // root-izq-der
        binaryTree2.getLeft().setRight(new BinaryTree<>(8));
        // ====================================================================
        // root
        intersectBT = new BinaryTree<>(3);
        // root-izq
        intersectBT.setLeft(new BinaryTree<>(4));
        // root-der
        intersectBT.setRight(new BinaryTree<>(9));
        // root-izq-izq
        intersectBT.getLeft().setLeft(new BinaryTree<>(5));
    }

    @Test
    void countDescendantsWithOnlyChild() {
        assertEquals(3, binaryTree0.countDescendantsWithOnlyChild());
    }

    @Test
    void findIntersection() {
        System.out.println("\nBinaryTree #1");
        binaryTree1.recorrerPreOrdenRecursive();
        System.out.println("\nBinaryTree #2");
        binaryTree2.recorrerPreOrdenRecursive();
        System.out.println("\nBinaryTree intersección #1 y #2");
        intersectBT.recorrerPreOrdenRecursive();
    }
}