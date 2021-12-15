package club.annt.tree;

import java.util.Comparator;

@SuppressWarnings("ClassHasNoToStringMethod")
public final class AVLTree<K extends Comparable<K>, V> {
    private AVLTreeNode<K, V> root;
    private Comparator<K>     cmp;

    /* constructores */
    public AVLTree() {}

    AVLTree(final K key, final V value) {
        root = new AVLTreeNode<>(key, value);
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
     * Inserta un nuevo elemento en el árbol respetando el órden de balanceo.
     *
     * @param key   órden del elemento
     * @param value elemento a insertar
     * @return {@code true} si la inserción se realizó correctamente,
     * {@code false} caso contrario
     */
    private AVLTree<K, V> insertAux(final K key, final V value) {
        if (isEmpty()) {
            root = new AVLTreeNode<>(key, value);
            return this;
        }

        final int keyCmp = cmp.compare(key, getKey());
        if (keyCmp == 0) {
            return this;
        }

        if (keyCmp < 0) {
            if (getLeft() == null) {
                setLeft(key, value);
            } else {
                getLeft().insert(key, value);
            }
        } else {
            if (getRight() == null) {
                setRight(key, value);
            } else {
                getRight().insert(key, value);
            }
        }

        updateHeight();
        return applyRotations();
    }

    public boolean insert(final K key, final V value) {
        final AVLTree<K, V> tree = insertAux(key, value);
        if (cmp.compare(tree.getKey(), getKey()) == 0) {
            return false;
        }

        root = insertAux(key, value).root;
        return true;
    }

    // public void delete(final K key) {
    //     if (isEmpty()) {
    //         return;
    //     }

    //     final int keyCmp = cmp.compare(key, getKey());
    //     if (keyCmp == 0) {

    //     }

    // }

    /**
     * Actualiza la altura del nodo que invoca este método.
     */
    private void updateHeight() {
        final int leftNHeight = getLeft() == null ? -1 : getLeft().height();
        final int rightNHeight = getRight() == null ? -1 : getRight().height();

        root.height = Math.max(leftNHeight, rightNHeight) + 1;
    }

    /**
     * Retorna la altura del nodo, se emplea este método auxiliar por si acaso
     * el método {@link #getHeight()} retorna un {@link NullPointerException}.
     *
     * @return altura del nodo
     */
    private int height() {
        return isEmpty() ? 0 : root.height;
    }

    /**
     * Retorna el valor de balanceo del nodo invocado.
     *
     * @return valor del balanceo
     */
    private int balance() {
        if (getLeft() == null && getRight() == null) {
            return 0;
        } else if (getLeft() != null && getRight() != null) {
            return getLeft().height() - getRight().height();
        } else if (getLeft() != null) {
            return getLeft().height();
        } else {
            return (-1) * getRight().height();
        }
    }

    private AVLTree<K, V> applyRotations() {
        final int balanceVal = balance();

        if (balanceVal > 1) {
            // left-right
            if (getLeft().balance() < 0) {
                setLeft(getLeft().rotateLeft());
            }

            // left-left
            return rotateRight();
        }
        if (balanceVal < -1) {
            // right-left
            if (getRight().balance() > 0) {
                setRight(getRight().rotateRight());
            }
            // right-right
            return rotateLeft();
        }

        return this;
    }

    private AVLTree<K, V> rotateLeft() {
        final AVLTree<K, V> newParentTree = getRight();
        setRight(newParentTree.getLeft());
        newParentTree.setLeft(this);
        updateHeight();
        newParentTree.updateHeight();

        return newParentTree;
    }

    private AVLTree<K, V> rotateRight() {
        final AVLTree<K, V> newParentTree = getLeft();
        setLeft(newParentTree.getRight());
        newParentTree.setRight(this);
        updateHeight();
        newParentTree.updateHeight();

        return newParentTree;
    }

    /* getters & setters */
    private K getKey() {
        return root.key;
    }

    private void setKey(final K key) {
        root.key = key;
    }

    public V getValue() {
        return root.value;
    }

    private void setValue(final V value) {
        root.value = value;
    }

    private int getHeight() {
        return root.height;
    }

    private void setHeight(final int height) {
        root.height = height;
    }

    public AVLTree<K, V> getLeft() {
        return root.left;
    }

    private void setLeft(final AVLTree<K, V> left) {
        root.left = left;
    }

    private void setLeft(final K key, final V value) {
        root.setLeft(key, value);
    }

    public AVLTree<K, V> getRight() {
        return root.right;
    }

    private void setRight(final AVLTree<K, V> right) {
        root.right = right;
    }

    private void setRight(final K key, final V value) {
        root.setRight(key, value);
    }

    @SuppressWarnings("ClassHasNoToStringMethod")
    private static final class AVLTreeNode<K extends Comparable<K>, V> {
        private K             key;
        private V             value;
        private int           height;
        private AVLTree<K, V> left;
        private AVLTree<K, V> right;

        /* constructores */
        private AVLTreeNode() {}

        AVLTreeNode(final K key, final V value, final AVLTree<K, V> left,
                    final AVLTree<K, V> right) {
            this.key = key;
            this.value = value;
            height = 1;
            this.left = left;
            this.right = right;
        }

        AVLTreeNode(final K key, final V value) {
            this(key, value, null, null);
        }

        /* getters & setters */
        private K getKey() {
            return key;
        }

        private void setKey(final K key) {
            this.key = key;
        }

        private V getValue() {
            return value;
        }

        private void setValue(final V value) {
            this.value = value;
        }

        private int getHeight() {
            return height;
        }

        private void setHeight(final int height) {
            this.height = height;
        }

        private AVLTree<K, V> getLeft() {
            return left;
        }

        private void setLeft(final AVLTree<K, V> left) {
            this.left = left;
        }

        private void setLeft(final K key, final V value) {
            left = new AVLTree<>(key, value);
        }

        private AVLTree<K, V> getRight() {
            return right;
        }

        private void setRight(final AVLTree<K, V> right) {
            this.right = right;
        }

        private void setRight(final K key, final V value) {
            right = new AVLTree<>(key, value);
        }
    }
}
