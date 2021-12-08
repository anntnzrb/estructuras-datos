package club.annt.tree;

import java.util.Comparator;

@SuppressWarnings("ClassHasNoToStringMethod")
public final class BSTree<E, K> {
    private BSTreeNode<E, K> root;
    private Comparator<K>    cmp;

    /* constructores */
    public BSTree() {}

    public BSTree(final E data) {
        root = new BSTreeNode<>(data);
    }

    /**
     * Verifica si el arbol está vació.
     *
     * @return {@code true} si el árbol está vacío, {@code false} caso contrario
     */
    public boolean isEmpty() {
        return root == null;
    }

    public BSTree<E, K> insertRecursive(final E elem, final Comparator<E> cmp) {
        if (isEmpty()) {
            return new BSTree<>(elem);
        }

        if (cmp.compare(elem, getData()) == 0) {
            return this;
        }
        if (cmp.compare(elem, getData()) > 0) {
            setRight(insertRecursive(elem, cmp));
        } else {
            setLeft(insertRecursive(elem, cmp));
        }

        return this;
    }

    public BSTreeNode<E, K> searchRecursive(final E elem,
                                            final Comparator<E> cmp) {
        if (isEmpty()) {
            return null;
        }

        if (cmp.compare(elem, getData()) == 0) {
            return root;
        }

        return cmp.compare(elem, getData()) > 0
               ? getLeft().searchRecursive(elem, cmp)
               : getRight().searchRecursive(elem, cmp);
    }

    /* getters & setters */
    public E getData() {
        return root.getData();
    }

    public BSTree<E, K> getLeft() {
        return root.getLeft();
    }

    public BSTree<E, K> getRight() {
        return root.getRight();
    }

    public void setData(final E data) {
        root.data = data;
    }

    public void setLeft(final BSTree<E, K> left) {
        root.left = left;
    }

    public void setRight(final BSTree<E, K> right) {
        root.right = right;
    }

    private static final class BSTreeNode<E, K> {
        private E            data;
        private BSTree<E, K> left;
        private BSTree<E, K> right;

        /* constructores */
        BSTreeNode() {}

        private BSTreeNode(final E data, final BSTree<E, K> left,
                           final BSTree<E, K> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        private BSTreeNode(final E data) {
            this(data, null, null);
        }

        /* getters & setters */
        public E getData() {
            return data;
        }

        public BSTree<E, K> getLeft() {
            return left;
        }

        public BSTree<E, K> getRight() {
            return right;
        }

        public void setData(final E data) {
            this.data = data;
        }

        public void setLeft(final BSTree<E, K> left) {
            this.left = left;
        }

        public void setRight(final BSTree<E, K> right) {
            this.right = right;
        }
    }
}
