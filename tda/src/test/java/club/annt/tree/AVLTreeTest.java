package club.annt.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class AVLTreeTest {
    AVLTree<Integer, Integer> avlTree0;

    @BeforeEach
    void setUp() {
        avlTree0 = new AVLTree<>(0, 0);
        avlTree0.insert(1, 1);
        avlTree0.insert(2, 2);
        avlTree0.insert(3, 3);
        avlTree0.insert(4, 4);
        avlTree0.insert(-1, -1);
    }

    @Test
    void lol() {
        System.out.println(avlTree0.getValue());
        System.out.println(avlTree0.getLeft().getValue());
        System.out.println(avlTree0.getRight().getValue());
    }
}