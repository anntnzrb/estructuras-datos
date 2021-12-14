package club.annt.leccioniii;

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
     *
     * @return {@code true} si el árbol está vacío, {@code false} caso contrario
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Verifica si el árbol es una hoja.
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
        System.out.println(getData());

        /* 2. recorrer izquierda */
        if (getLeft() != null) {
            getLeft().recorrerPreOrdenRecursive();
        }

        /* 3. recorrer derecha */
        if (getRight() != null) {
            getRight().recorrerPreOrdenRecursive();
        }
    }

    /* ************************************************************************
     * Ej #1
     * *********************************************************************** */
    public int countDescendantsWithOnlyChild() {
        if (isEmpty()) {
            return 0;
        }

        /*
         * (^) === disyunción exclusiva
         *
         * se inicia el contador en 1 si es que:
         * => NO hay hijo izquierdo, y SI hay hijo derecho
         * ó
         * => SI hay hijo izquierdo, y NO hay hijo derecho
         *
         * se inicia el contador en 0 si es que:
         * => SI hay hijo izquierdo, y SI hay hijo derecho
         * ó
         * => NO hay hijo izquierdo, y NO hay hijo derecho
         */
        int count = getLeft() != null ^ getRight() != null ? 1 : 0;
        if (getLeft() != null) {
            count += getLeft().countDescendantsWithOnlyChild();
        }
        if (getRight() != null) {
            count += getRight().countDescendantsWithOnlyChild();
        }

        return count;
    }

    /* ************************************************************************
     * Ej #2
     * *********************************************************************** */
    public static BinaryTree<Integer> findIntersection(
            final BinaryTree<Integer> intBT1,
            final BinaryTree<Integer> intBT2) {
        if (intBT1 == null || intBT2 == null) {
            return null;
        }

        final BinaryTree<Integer> intersectBT =
                new BinaryTree<>(intBT1.getData() + intBT2.getData());
        intersectBT.setLeft(findIntersection(
                intBT1.getLeft(), intBT2.getLeft()));
        intersectBT.setRight(findIntersection(
                intBT1.getRight(), intBT2.getRight()));

        return intersectBT;
    }

    /* getters & setters */
    public BinaryTreeNode<E> getRoot() {
        return root;
    }

    public void setRoot(final BinaryTreeNode<E> root) {
        this.root = root;
    }

    public E getData() {
        return root.data;
    }

    public BinaryTree<E> getLeft() {
        return root.left;
    }

    public void setLeft(final BinaryTree<E> tree) {
        root.setLeft(tree);
    }

    public BinaryTree<E> getRight() {
        return root.right;
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