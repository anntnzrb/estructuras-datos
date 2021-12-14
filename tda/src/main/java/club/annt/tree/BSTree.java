package club.annt.tree;

import java.util.Comparator;

@SuppressWarnings("ClassHasNoToStringMethod")
public final class BSTree<E, K extends Comparable<K>> {
    private BSTreeNode<E, K> root;
    private Comparator<K>    cmp;

    /* constructores */
    public BSTree() {}

    public BSTree(final E data, final K key) {
        root = new BSTreeNode<>(data, key);
        cmp = Comparator.naturalOrder();
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

    public E getMin() {
        if (isEmpty()) {
            return null;
        }

        E minElem = getData();
        if (getLeft() != null) {
            minElem = getLeft().getMin();
        }

        return minElem;
    }

    public E getMax() {
        if (isEmpty()) {
            return null;
        }

        E maxElem = getData();
        if (getRight() != null) {
            maxElem = getRight().getMax();
        }

        return maxElem;
    }

    public boolean insertRecursive(final E elem, final K key) {
        if (isEmpty()) {
            root = new BSTreeNode<>(elem, key);
            return true;
        }

        if (cmp.compare(key, getKey()) == 0) {
            return false;
        }

        if (cmp.compare(key, getKey()) < 0) {
            if (getLeft() == null) {
                setLeft(elem, key);
            } else {
                getLeft().insertRecursive(elem, key);
            }
        } else {
            if (getRight() == null) {
                setRight(elem, key);
            } else {
                getRight().insertRecursive(elem, key);
            }
        }

        return true;
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

    public K getKey() {
        return root.key;
    }

    public BSTree<E, K> getLeft() {
        return root.getLeft();
    }

    public void setLeft(final E elem, final K key) {
        root.setLeft(elem, key);
    }

    public BSTree<E, K> getRight() {
        return root.getRight();
    }

    public void setRight(final E elem, final K key) {
        root.setRight(elem, key);
    }

    private static final class BSTreeNode<E, K extends Comparable<K>> {
        private E            data;
        private K            key;
        private BSTree<E, K> left;
        private BSTree<E, K> right;

        /* constructores */
        BSTreeNode() {}

        BSTreeNode(final E data, final K key, final BSTree<E, K> left,
                   final BSTree<E, K> right) {
            this.data = data;
            this.key = key;
            this.left = left;
            this.right = right;
        }

        BSTreeNode(final E data, final K key) {
            this(data, key, null, null);
        }

        /* getters & setters */
        public E getData() {
            return data;
        }

        public void setData(final E data) {
            this.data = data;
        }

        public K getKey() {
            return key;
        }

        public void setKey(final K key) {
            this.key = key;
        }

        public BSTree<E, K> getLeft() {
            return left;
        }

        public void setLeft(final E elem, final K key) {
            left = new BSTree<>(elem, key);
        }

        public BSTree<E, K> getRight() {
            return right;
        }

        public void setRight(final E elem, final K key) {
            right = new BSTree<>(elem, key);
        }
    }
}
