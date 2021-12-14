package club.annt.tree;

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