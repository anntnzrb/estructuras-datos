package club.annt.tree;

@SuppressWarnings("ClassHasNoToStringMethod")
final class NodeBinaryTree<E> {
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
