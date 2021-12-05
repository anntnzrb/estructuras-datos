package club.annt.tree;

import java.util.ArrayDeque;
import java.util.Deque;

@SuppressWarnings("ClassHasNoToStringMethod")
public final class BinaryTree<E> {
    private NodeBinaryTree<E> root;

    /* constructores */
    public BinaryTree() {}

    public BinaryTree(final NodeBinaryTree<E> root) {
        this.root = root;
    }

    public BinaryTree(final E data) {
        root = new NodeBinaryTree<>(data);
    }

    /**
     * Verifica si el arbol está vació.
     * <p>
     * Complejidad: O(1)
     *
     * @return {@code true} si el árbol está vacío, {@code false} caso contrario
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Verifica si el árbol es una hoja.
     * <p>
     * Complejidad: O(1)
     *
     * @return {@code true} si el árbol es hoja , {@code false} caso contrario
     */
    public boolean isLeaf() {
        return !isEmpty() && root.getLeft() == null && root.getRight() == null;
    }

    /**
     * Método que recorrer el arbol en el siguiente órden:
     * raíz - árbol derecho - árbol izquierdo
     * <p>
     * NOTA: Éste método está implentado de forma "recursiva".
     */
    public void recorrerPreOrdenRecursive() {
        if (isEmpty()) {
            return;
        }

        /* 1. raíz */
        System.out.println(root.getData());

        /* 2. recorrer izquierda */
        if (root.getLeft() != null) {
            root.getLeft().recorrerPreOrdenRecursive();
        }

        /* 3. recorrer derecha */
        if (root.getRight() != null) {
            root.getRight().recorrerPreOrdenRecursive();
        }
    }

    /**
     * Método que recorrer el arbol en el siguiente órden:
     * árbol derecho - raíz - árbol izquierdo
     * <p>
     * NOTA: Éste método está implentado de forma "recursiva".
     */
    public void recorrerEnOrdenRecursive() {
        if (isEmpty()) {
            return;
        }

        /* 1. recorrer izquierda */
        if (root.getLeft() != null) {
            root.getLeft().recorrerEnOrdenRecursive();
        }

        /* 2. raíz */
        System.out.println(root.getData());

        /* 3. recorrer derecha */
        if (root.getRight() != null) {
            root.getRight().recorrerEnOrdenRecursive();
        }

    }

    /**
     * Método que recorrer el arbol en el siguiente órden:
     * árbol derecho - árbol izquierdo - raíz
     * <p>
     * NOTA: Éste método está implentado de forma "recursiva".
     */
    public void recorrerPostOrdenRecursive() {
        if (isEmpty()) {
            return;
        }

        /* 1. recorrer izquierda */
        if (root.getLeft() != null) {
            root.getLeft().recorrerPostOrdenRecursive();
        }

        /* 2. recorrer derecha */
        if (root.getRight() != null) {
            root.getRight().recorrerPostOrdenRecursive();
        }

        /* 3. raíz */
        System.out.println(root.getData());
    }

    /**
     * Cuenta el número de hojas que tiene el árbol.
     * <p>
     * NOTA: Éste método está implentado de forma "recursiva".
     *
     * @return número de hojas que tiene el árbol
     */
    public int countLeavesRecursive() {
        if (isEmpty()) {
            return 0;
        } else if (isLeaf()) {
            return 1;
        }

        int leavesLeft = 0;
        int leavesRight = 0;

        if (root.getLeft() != null) {
            leavesLeft = root.getLeft().countLeavesRecursive();
        }
        if (root.getRight() != null) {
            leavesRight = root.getRight().countLeavesRecursive();
        }

        return leavesLeft + leavesRight;
    }

    /**
     * Cuenta el número de hojas que tiene el árbol.
     * <p>
     * NOTA: Éste método está implentado de forma "iterativa".
     *
     * @return número de hojas que tiene el árbol
     */
    @SuppressWarnings({"CollectionWithoutInitialCapacity",
            "MethodCallInLoopCondition"})
    public int countLeavesIterative() {
        if (isEmpty()) {
            return 0;
        }

        int totalLeaves = 0;
        final Deque<BinaryTree<E>> stack = new ArrayDeque<>();
        stack.push(this);
        while (!stack.isEmpty()) {
            final BinaryTree<E> subBT = stack.pop();
            if (subBT.isLeaf()) {
                ++totalLeaves;
            } else {
                if (subBT.root.getLeft() != null) {
                    stack.push(subBT.root.getLeft());
                }
                if (subBT.root.getRight() != null) {
                    stack.push(subBT.root.getRight());
                }
            }

        }

        return totalLeaves;
    }

    /* getters & setters */
    public NodeBinaryTree<E> getRoot() {
        return root;
    }

    public void setRoot(final NodeBinaryTree<E> root) {
        this.root = root;
    }

    public BinaryTree<E> getLeft() {
        return root.getLeft();
    }

    public void setLeft(final BinaryTree<E> tree) {
        root.setLeft(tree);
    }

    public BinaryTree<E> getRight() {
        return root.getRight();
    }

    public void setRight(final BinaryTree<E> tree) {
        root.setRight(tree);
    }

    private static final class NodeBinaryTree<E> {
        private E             data;
        private BinaryTree<E> left;
        private BinaryTree<E> right;

        /* constructores */
        NodeBinaryTree() {}

        NodeBinaryTree(final E data, final BinaryTree<E> left,
                       final BinaryTree<E> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        NodeBinaryTree(final E elem) {
            this(elem, null, null);
        }

        /* getters & setters */
        public E getData() {
            return data;
        }

        public BinaryTree<E> getLeft() {
            return left;
        }

        public void setLeft(final BinaryTree<E> left) {
            this.left = left;
        }

        public BinaryTree<E> getRight() {
            return right;
        }

        public void setRight(final BinaryTree<E> right) {
            this.right = right;
        }
    }
}