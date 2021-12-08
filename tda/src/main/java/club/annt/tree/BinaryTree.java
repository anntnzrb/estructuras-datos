package club.annt.tree;

import java.util.*;

@SuppressWarnings({"ClassHasNoToStringMethod", "EqualsAndHashcode",
        "ClassWithTooManyMethods", "OverlyComplexClass", "BoundedWildcard",
        "unused"})
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
        if (getLeft() != null) {
            getLeft().recorrerEnOrdenRecursive();
        }

        /* 2. raíz */
        System.out.println(getData());

        /* 3. recorrer derecha */
        if (getRight() != null) {
            getRight().recorrerEnOrdenRecursive();
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
        if (getLeft() != null) {
            getLeft().recorrerPostOrdenRecursive();
        }

        /* 2. recorrer derecha */
        if (getRight() != null) {
            getRight().recorrerPostOrdenRecursive();
        }

        /* 3. raíz */
        System.out.println(getData());
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

        if (getLeft() != null) {
            leavesLeft = getLeft().countLeavesRecursive();
        }
        if (getRight() != null) {
            leavesRight = getRight().countLeavesRecursive();
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
                if (subBT.getLeft() != null) {
                    stack.push(subBT.getLeft());
                }
                if (subBT.getRight() != null) {
                    stack.push(subBT.getRight());
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
        } else if (cmp.compare(getData(), data) == 0) {
            return root;
        }

        BinaryTreeNode<E> tmp = null;
        if (getLeft() != null) {
            tmp = getLeft().searchRecursive(data, cmp);
        }
        if (tmp == null && getRight() != null) {
            return getRight().searchRecursive(data, cmp);
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

        E minElem = getData();
        if (getLeft() != null) {
            final E minLeft = getLeft().getMinRecursive(cmp);
            minElem = cmp.compare(minElem, minLeft) > 0 ? minLeft : minElem;
        }
        if (getRight() != null) {
            final E minRight = getRight().getMinRecursive(cmp);
            minElem = cmp.compare(minElem, minRight) > 0 ? minRight : minElem;
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

        E minElem = getData();

        final Deque<BinaryTree<E>> stack = new ArrayDeque<>();
        stack.push(this);
        while (true) {
            final boolean isStackEmpty = stack.isEmpty();
            if (isStackEmpty) {
                break;
            }

            final BinaryTree<E> subBT = stack.pop();
            final E data = subBT.getData();
            minElem = cmp.compare(minElem, data) > 0 ? data : minElem;

            if (subBT.getLeft() != null) {
                stack.push(subBT.getLeft());
            }
            if (subBT.getRight() != null) {
                stack.push(subBT.getRight());
            }
        }

        return minElem;
    }

    /**
     * Cuenta la cantidad de descendientes que tiene un árbol.
     * <p>
     * NOTA: Éste método está implentado de forma "recursiva".
     *
     * @return número de descendientes
     */
    public int countDescendantsRecursive() {
        if (isEmpty() || isLeaf()) {
            return 0;
        }

        int descendants = 0;
        if (getLeft() != null) {
            descendants += getLeft().countDescendantsRecursive() + 1;
        }
        if (getRight() != null) {
            descendants += getRight().countDescendantsRecursive() + 1;
        }

        return descendants;
    }

    /**
     * Cuenta la cantidad de descendientes que tiene un árbol.
     * <p>
     * NOTA: Éste método está implentado de forma "iterativa".
     *
     * @return número de descendientes
     */
    @SuppressWarnings("CollectionWithoutInitialCapacity")
    public int countDescendantsIterative() {
        if (isEmpty() || isLeaf()) {
            return 0;
        }

        int descendants = 0;
        final Deque<BinaryTree<E>> stack = new ArrayDeque<>();
        stack.push(this);
        while (true) {
            final boolean isStackEmpty = stack.isEmpty();
            if (isStackEmpty) {
                break;
            }

            final BinaryTree<E> subBT = stack.pop();
            if (subBT.getLeft() != null) {
                ++descendants;
                stack.push(subBT.getLeft());
            }
            if (subBT.getRight() != null) {
                ++descendants;
                stack.push(subBT.getRight());
            }
        }

        return descendants;
    }

    /**
     * Dado un {@link BinaryTreeNode}, retorna su padre correspondiente.
     * <p>
     * NOTA: Éste método está implentado de forma "recursiva".
     *
     * @param binaryTreeNode {@link BinaryTreeNode} al cual buscar el padre
     * @return {@link BinaryTreeNode} padre
     */
    public BinaryTreeNode<E> findParentRecursive(final BinaryTreeNode<E> binaryTreeNode) {
        BinaryTreeNode<E> parent = null;

        if (getLeft() != null) {
            if (getLeft().getLeft().root.equals(binaryTreeNode)) {
                return root;
            }

            parent = getLeft().findParentRecursive(binaryTreeNode);
        }
        if (getRight() != null) {
            if (getRight().getRight().root.equals(binaryTreeNode)) {
                return root;
            }

            parent = getRight().findParentRecursive(binaryTreeNode);
        }

        return parent;
    }

    /**
     * Dado un {@link BinaryTreeNode}, retorna su padre correspondiente.
     * <p>
     * NOTA: Éste método está implentado de forma "iterativa".
     *
     * @param binaryTreeNode {@link BinaryTreeNode} al cual buscar el padre
     * @return {@link BinaryTreeNode} padre
     */
    @SuppressWarnings("CollectionWithoutInitialCapacity")
    public BinaryTreeNode<E> findParentIterative(final E binaryTreeNode,
                                                 final Comparator<E> cmp) {

        final Deque<BinaryTree<E>> stack = new ArrayDeque<>();
        stack.push(this);

        if (!isEmpty() && !isLeaf()) {
            while (true) {
                final boolean isStackEmpty = stack.isEmpty();
                if (isStackEmpty) {
                    break;
                }

                final BinaryTree<E> subBT = stack.pop();

                if (subBT.getLeft() != null) {
                    if (cmp.compare(binaryTreeNode, subBT.getLeft().getData())
                        == 0) {
                        return subBT.root;
                    }
                    stack.push(subBT.getLeft());
                }

                if (subBT.getRight() != null) {
                    if (cmp.compare(binaryTreeNode, subBT.getRight().getData())
                        == 0) {
                        return subBT.root;
                    }

                    stack.push(subBT.getRight());
                }
            }
        }

        return stack.pop().root;
    }

    /**
     * Retorna la cantidad de niveles que tiene el árbol.
     * <p>
     * NOTA: Éste método está implentado de forma "recursiva".
     *
     * @return cantidad de niveles del árbol
     */
    public int countLevelsRecursive() {
        if (isEmpty()) {
            return 0;
        } else if (isLeaf()) {
            return 1;
        }

        /*
         * al iterar, van a existir ocasiones en la cual un nivel ya registrado
         * del lado izquierdo, también se registre del lado derecho.
         * retorna el lado que tenga el nivel mas alto, olvidando el otro valor.
         */
        int levelsLeft = 0;
        int levelsRight = 0;

        if (getLeft() != null) {
            levelsLeft = getLeft().countLevelsRecursive();
        }
        if (getRight() != null) {
            levelsRight = getRight().countLevelsRecursive();
        }

        return Math.max(levelsLeft, levelsRight) + 1;
    }

    /**
     * Retorna la cantidad de niveles que tiene el árbol.
     * <p>
     * NOTA: Éste método está implentado de forma "iterativa".
     *
     * @return cantidad de niveles del árbol
     */
    @SuppressWarnings("CollectionWithoutInitialCapacity")
    public int countLevelsIterative() {
        if (isEmpty()) {
            return 0;
        } else if (isLeaf()) {
            return 1;
        }

        int levels = 0;
        final Deque<BinaryTree<E>> stack = new ArrayDeque<>();
        stack.push(this);
        while (true) {
            final boolean isStackEmpty = stack.isEmpty();
            if (isStackEmpty) {
                break;
            }

            final BinaryTree<E> subBT = stack.pop();
            if (subBT.getLeft() != null) {
                stack.push(subBT.getLeft());
            }
            if (subBT.getRight() != null) {
                stack.push(subBT.getRight());
            }
            ++levels;
        }

        return levels;
    }

    /**
     * Un árbol binario es zurdo si:
     * 1) está vacío
     * 2) es una hoja
     * 3) si sus hijos son ambos zurdos y tiene a más de la mitad de sus
     * descendientes en el hijo izquierdo
     * <p>
     * NOTA: Éste método está implentado de forma "recursiva".
     *
     * @return {@code true} si el árbol es zurdo, {@code false} caso contrario
     */
    public boolean isLeftyRecursive() {
        if (isEmpty() || isLeaf()) {
            return true;
        }

        /*
         * NO tiene hijo zurdo
         * el hijo del hijo zurdo NO es zurdo
         * tiene hijo diestro, pero éste hijo diestro NO es zurdo (absurdo)
         * --------------------------------------------------------------------
         * ==> false
         */
        if (getLeft() == null
            || !getLeft().isLeftyRecursive()
            || (getRight() != null && !getRight().isLeftyRecursive())) {
            return false;
        }

        /*
         * si el número de descendientes del hijo zurdo es mayor que la mitad
         * de descendientes totales
         * --------------------------------------------------------------------
         * ==> true
         */
        return getLeft().countDescendantsRecursive() + 1
               > countDescendantsRecursive() >> 1;
    }

    /**
     * Un árbol binario es zurdo si:
     * 1) está vacío
     * 2) es una hoja
     * 3) si sus hijos son ambos zurdos y tiene a más de la mitad de sus
     * descendientes en el hijo izquierdo
     * <p>
     * NOTA: Éste método está implentado de forma "iterativa".
     *
     * @return {@code true} si el árbol es zurdo, {@code false} caso contrario
     */
    @SuppressWarnings({"CollectionWithoutInitialCapacity", "rawtypes"})
    public boolean isLeftyIterative() {
        if (isEmpty() || isLeaf()) {
            return true;
        }

        final Deque<BinaryTree<E>> stack = new ArrayDeque<>();
        stack.push(this);

        while (true) {
            final boolean isStackEmpty = stack.isEmpty();
            if (isStackEmpty) {
                break;
            }

            final BinaryTree<E> subBT = stack.pop();
            final int descendants = subBT.countDescendantsRecursive();
            if (subBT.getLeft() != null) {
                final int leftDescendants = subBT.getLeft()
                                                 .countDescendantsRecursive();
                if ((descendants >> 1) >= leftDescendants + 1) {
                    return false;
                }

                stack.push(subBT.getLeft());

                if (subBT.getRight() != null) {
                    stack.push(subBT.getRight());
                }
            }
        }

        return true;
    }

    /**
     * Verifica si árbol actual es idéntico al pasado por parámetro.
     * <p>
     * NOTA: Éste método está implentado de forma "recursiva".
     *
     * @param tree árbol contra el cual comprar
     * @return {@code true} si el árbol es idéntico, {@code false} caso
     * contrario
     */
    public boolean isIdenticalRecursive(final BinaryTree<E> tree) {
        /*
         * ambos son vacíos
         * ó
         * el objeto es idéntico (usando equals())
         * --------------------------------------------------------------------
         * ==> true
         */
        if (isEmpty() && tree.isEmpty()
            || equals(tree)) {
            return true;
        }

        boolean isLeftEq = false;
        boolean isRightEq = false;
        if (getLeft() != null && tree.getLeft() != null) {
            isLeftEq = getLeft().isIdenticalRecursive(tree.getLeft());
        }
        if (getRight() != null && tree.getRight() != null) {
            isRightEq = getRight().isIdenticalRecursive(tree.getRight());
        }

        return isLeftEq && isRightEq;
    }

    /**
     * Verifica si árbol actual es idéntico al pasado por parámetro.
     * <p>
     * NOTA: Éste método está implentado de forma "iterativa".
     *
     * @param tree árbol contra el cual comprar
     * @return {@code true} si el árbol es idéntico, {@code false} caso
     * contrario
     */
    @SuppressWarnings({"CollectionWithoutInitialCapacity",
            "MethodCallInLoopCondition"})
    public boolean isIdenticalIterative(final BinaryTree<E> tree,
                                        final Comparator<E> cmp) {
        if (isEmpty() && tree.isEmpty()) {
            return true;
        }

        final Deque<BinaryTree<E>> stackThis = new ArrayDeque<>();
        final Deque<BinaryTree<E>> stackOther = new ArrayDeque<>();

        stackThis.push(this);
        stackOther.push(tree);

        while (!stackThis.isEmpty() && !stackOther.isEmpty()) {
            final BinaryTree<E> tTree = stackThis.pop();
            final BinaryTree<E> oTree = stackOther.pop();

            if (cmp.compare(tTree.getData(), oTree.getData()) != 0) {
                return false;
            }

            if (tTree.getLeft() != null) {
                stackThis.push(tTree.getLeft());
            }
            if (oTree.getLeft() != null) {
                stackOther.push(oTree.getLeft());
            }

            if (tTree.getRight() != null) {
                stackThis.push(tTree.getRight());
            }
            if (oTree.getRight() != null) {
                stackOther.push(oTree.getRight());
            }
        }

        return stackThis.isEmpty() && stackOther.isEmpty();
    }

    public void largestValueOfEachLevelRecursive(final Comparator<BinaryTree<E>> cmp) {
        if (isEmpty()) {
            return;
        }
    }

    @SuppressWarnings({"ObjectAllocationInLoop", "MethodCallInLoopCondition",
            "OverlyLongLambda"})
    public void largestValueOfEachLevelIterative(final Comparator<BinaryTree<E>> cmp) {
        if (isEmpty()) {
            return;
        }

        final Queue<BinaryTree<E>> pqBT = new PriorityQueue<>(cmp);
        pqBT.offer(this);

        while (!pqBT.isEmpty()) {
            System.out.println(pqBT.peek().getData());
            final Collection<BinaryTree<E>> listSubTrees = new LinkedList<>();
            while (!pqBT.isEmpty()) {
                listSubTrees.add(pqBT.poll());
            }

            listSubTrees.forEach(subBT -> {
                if (subBT.getLeft() != null) {
                    pqBT.offer(subBT.getLeft());
                }
                if (subBT.getRight() != null) {
                    pqBT.offer(subBT.getRight());
                }
            });
        }
    }

    /**
     * Retorna el número de nodos que contienen únicamente 1 hijo.
     * <p>
     * NOTA: Éste método está implentado de forma "recursiva".
     * <p>
     * Referencias:
     * - (método corto) https://stackoverflow.com/a/37935129
     * - (método explícito) https://stackoverflow.com/a/37936804
     *
     * @return número de nodos que tienen 1 solo hijo
     */
    public int countNodesWithOnlyChildRecursive() {
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
            count += getLeft().countNodesWithOnlyChildRecursive();
        }
        if (getRight() != null) {
            count += getRight().countNodesWithOnlyChildRecursive();
        }

        return count;
    }

    /**
     * Retorna el número de nodos que contienen únicamente 1 hijo.
     * <p>
     * NOTA: Éste método está implentado de forma "iterativa".
     * <p>
     *
     * @return número de nodos que tienen 1 solo hijo
     */
    @SuppressWarnings("CollectionWithoutInitialCapacity")
    public int countNodesWithOnlyChildIterative() {
        if (isEmpty()) {
            return 0;
        }

        int count = 0;
        final Deque<BinaryTree<E>> stack = new ArrayDeque<>();
        stack.push(this);
        while (true) {
            final boolean isStackEmpty = stack.isEmpty();
            if (isStackEmpty) {
                break;
            }

            final BinaryTree<E> subBT = stack.pop();

            count = subBT.getLeft() != null ^ subBT.getRight() != null
                    ? count + 1
                    : count;

            if (subBT.getLeft() != null) {
                stack.push(subBT.getLeft());
            }
            if (subBT.getRight() != null) {
                stack.push(subBT.getRight());
            }
        }

        return count;
    }

    /**
     * Verifica si la altura del árbol está balanceada.
     * <p>
     * Un árbol no vacío está balanceado en altura si:
     * 1. Su sub-árbol izquierdo está balanceado
     * 2. Su sub-árbol derecho está balanceado
     * 3. La diferencia entre las alturas de sus sub-árboles izquierdo y derecho
     * es menor que 1.
     * <p>
     * NOTA: Un árbol vacío siempre está balanceado en altura.
     * <p>
     * NOTA: Éste método está implentado de forma "recursiva".
     *
     * @return {@code true} si el árbol está balanceado en altura,
     * {@code false} caso contrario
     */
    public boolean isHeightBalancedRecursive() {
        if (isEmpty()) {
            return true;
        }

        /*
         * tiene hijo izquierdo, pero éste no está balanceado
         * ó
         * tiene hijo derecho, pero éste no está balanceado
         * --------------------------------------------------------------------
         * ==> false
         */
        if ((getLeft() != null && !getLeft().isHeightBalancedRecursive())
            || (getRight() != null
                && !getRight().isHeightBalancedRecursive())) {
            return false;
        }

        /*
         * no tiene hijo izquierdo
         * ó
         * no tiene hijo derecho
         * ó
         * La diferencia entre las alturas de izquierda y derecha es menor o
         * igual a 1
         * --------------------------------------------------------------------
         * ==> false
         */
        return getLeft() == null
               || getRight() == null
               || Math.abs(getLeft().getHeight() - getRight().getHeight()) <= 1;
    }

    /**
     * Verifica si la altura del árbol está balanceada.
     * <p>
     * Un árbol no vacío está balanceado en altura si:
     * 1. Su sub-árbol izquierdo está balanceado
     * 2. Su sub-árbol derecho está balanceado
     * 3. La diferencia entre las alturas de sus sub-árboles izquierdo y derecho
     * es menor que 1.
     * <p>
     * NOTA: Un árbol vacío siempre está balanceado en altura.
     * <p>
     * NOTA: Éste método está implentado de forma "iterativa".
     *
     * @return {@code true} si el árbol está balanceado en altura,
     * {@code false} caso contrario
     */
    @SuppressWarnings("CollectionWithoutInitialCapacity")
    public boolean isHeightBalancedIterative() {
        if (isEmpty()) {
            return true;
        }

        final Deque<BinaryTree<E>> stack = new ArrayDeque<>();
        stack.push(this);
        while (true) {
            final boolean isStackEmpty = stack.isEmpty();
            if (isStackEmpty) {
                break;
            }

            final BinaryTree<E> subBT = stack.pop();
            if (subBT.getLeft() != null) {
                stack.push(subBT.getLeft());
            }
            if (subBT.getRight() != null) {
                stack.push(subBT.getRight());
            }

            // TODO
        }

        return false;
    }

    /**
     * Obtiene la altura de un árbol
     *
     * @return altura del árbol
     */
    private int getHeight() {
        return countLevelsRecursive() - 1;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        final BinaryTree<?> otherTree = (BinaryTree<?>) obj;

        return Objects.equals(root, otherTree.root);
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