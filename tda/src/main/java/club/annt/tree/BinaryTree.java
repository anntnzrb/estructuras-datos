package club.annt.tree;

import java.util.ArrayDeque;
import java.util.Comparator;
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

    public NodeBinaryTree<E> searchRecursive(final E data,
                                             final Comparator<E> cmp) {
        if (isEmpty()) {
            return null;
        } else if (cmp.compare(root.getData(), data) == 0) {
            return root;
        }

        NodeBinaryTree<E> tmp = null;
        if (root.getLeft() != null) {
            tmp = root.getLeft().searchRecursive(data, cmp);
        }
        if (tmp == null && root.getRight() != null) {
            return root.getRight().searchRecursive(data, cmp);
        }

        return tmp;
    }

    @SuppressWarnings({"MethodCallInLoopCondition",
            "CollectionWithoutInitialCapacity"})
    public NodeBinaryTree<E> searchIterative(final E data,
                                             final Comparator<E> cmp) {
        if (isEmpty()) {
            return null;
        }

        final Deque<NodeBinaryTree<E>> stackBN = new ArrayDeque<>();
        stackBN.push(root);
        while (!stackBN.isEmpty()) {
            final NodeBinaryTree<E> tmp = stackBN.pop();
            if (cmp.compare(tmp.getData(), data) == 0) {
                return root;
            }
            if (tmp.getLeft() != null) {
                stackBN.push(tmp.getLeft().root);
            }
            if (tmp.getRight() != null) {
                stackBN.push(tmp.getRight().root);
            }
        }

        return null;
    }

    public E getMinRecursive(final Comparator<E> cmp) {
        if (isEmpty()) {
            return null;
        }

        E minElem = root.getData();
        if (root.getLeft() != null) {
            final E minLeft = root.getLeft().getMinRecursive(cmp);
            if (cmp.compare(root.getData(), minLeft) > 0) {
                minElem = minLeft;
            }
        }
        if (root.getRight() != null) {
            final E minRight = root.getRight().getMinRecursive(cmp);
            if (cmp.compare(root.getData(), minRight) > 0) {
                minElem = minRight;
            }
        }

        return minElem;
    }

    @SuppressWarnings({"CollectionWithoutInitialCapacity",
            "MethodCallInLoopCondition"})
    public E getMinIterative(final Comparator<E> cmp) {
        if (isEmpty()) {
            return null;
        }

        E minElem = root.getData();
        final Deque<NodeBinaryTree<E>> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            final NodeBinaryTree<E> tmp = stack.pop();
            if (cmp.compare(minElem, tmp.getData()) > 0) {
                stack.pop();
                minElem = tmp.getData();
            }
            if (root.getLeft() != null) {
                stack.push(root.getLeft().root);
            }

            if (root.getRight() != null) {
                stack.push(root.getRight().root);
            }
        }

        return minElem;
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