package club.annt.tree;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;

@SuppressWarnings("ClassHasNoToStringMethod")
public final class BinaryTree<E> {
    private BinaryTreeNode<E> root;

    /* constructores */
    public BinaryTree() {}

    public BinaryTree(final BinaryTreeNode<E> root) {
        this.root = root;
    }

    public BinaryTree(final E data) {
        root = new BinaryTreeNode<>(data);
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

    /**
     * Busca el elemento pasado por parámetro en el árbol
     * <p>
     * NOTA: Éste método está implentado de forma "recursiva".
     *
     * @param data elemento a buscar
     * @param cmp  {@link Comparator}
     * @return {@link BinaryTreeNode} que contiene al elemento
     */
    public BinaryTreeNode<E> searchRecursive(final E data,
                                             final Comparator<E> cmp) {
        if (isEmpty()) {
            return null;
        } else if (cmp.compare(root.getData(), data) == 0) {
            return root;
        }

        BinaryTreeNode<E> tmp = null;
        if (root.getLeft() != null) {
            tmp = root.getLeft().searchRecursive(data, cmp);
        }
        if (tmp == null && root.getRight() != null) {
            return root.getRight().searchRecursive(data, cmp);
        }

        return tmp;
    }

    /**
     * Busca el elemento pasado por parámetro en el árbol
     * <p>
     * NOTA: Éste método está implentado de forma "iterativa".
     *
     * @param data elemento a buscar
     * @param cmp  {@link Comparator}
     * @return {@link BinaryTreeNode} que contiene al elemento
     */
    @SuppressWarnings("CollectionWithoutInitialCapacity")
    public BinaryTreeNode<E> searchIterative(final E data,
                                             final Comparator<E> cmp) {
        if (isEmpty()) {
            return null;
        }

        final Deque<BinaryTreeNode<E>> stackBN = new ArrayDeque<>();
        stackBN.push(root);
        while (true) {
            final boolean isStackEmpty = stackBN.isEmpty();
            if (isStackEmpty) {
                break;
            }

            final BinaryTreeNode<E> tmp = stackBN.pop();
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

    /**
     * Retorna el mínimo valor del árbol.
     * <p>
     * NOTA: Éste método está implentado de forma "recursiva".
     *
     * @param cmp {@link Comparator}
     * @return el valor mínimo del arbol
     */
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

    /**
     * Retorna el mínimo valor del árbol.
     * <p>
     * NOTA: Éste método está implentado de forma "iterativa".
     *
     * @param cmp {@link Comparator}
     * @return el valor mínimo del arbol
     */
    @SuppressWarnings("CollectionWithoutInitialCapacity")
    public E getMinIterative(final Comparator<E> cmp) {
        if (isEmpty()) {
            return null;
        }

        E minElem = root.getData();
        final Deque<BinaryTree<E>> stack = new ArrayDeque<>();
        stack.push(this);

        while (true) {
            final boolean isStackEmpty = stack.isEmpty();
            if (isStackEmpty) {
                break;
            }

            final BinaryTree<E> subBT = stack.pop();
            final E data = subBT.root.getData();

            minElem = cmp.compare(minElem, data) > 0 ? data : minElem;

            if (subBT.root.getLeft() != null) {
                stack.push(subBT.root.getRight());
            }

            if (subBT.root.getRight() != null) {
                stack.push(subBT.root.getRight());
            }
        }

        return minElem;
    }

    /* getters & setters */
    public BinaryTreeNode<E> getRoot() {
        return root;
    }

    public void setRoot(final BinaryTreeNode<E> root) {
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

    private static final class BinaryTreeNode<E> {
        private E             data;
        private BinaryTree<E> left;
        private BinaryTree<E> right;

        /* constructores */
        BinaryTreeNode() {}

        BinaryTreeNode(final E data, final BinaryTree<E> left,
                       final BinaryTree<E> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        BinaryTreeNode(final E elem) {
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