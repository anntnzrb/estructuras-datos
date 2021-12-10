package club.annt.huffman;

import club.annt.tree.BinaryTree;

import java.util.*;

public final class Huffman {
    public static Map<Character, Integer> getFrecuencies(final String str) {
        final Map<Character, Integer> map = new LinkedHashMap<>(str.length());

        /* iterar a través del string */
        for (final char ch : str.toCharArray()) {
            /*
             * crear la clave (char) con valor 1 si no existe
             * ú obtenerla y sumarle 1
             */
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        return map;
    }

    @SuppressWarnings("ObjectAllocationInLoop")
    public static BinaryTree<HuffmanInfo> buildHuffmanTree(final Map<Character,
            Integer> map) {
        /* PQ con frecuencias del contenido de cada raíz (ascendentemente) */
        final Queue<BinaryTree<HuffmanInfo>> pq =
                new PriorityQueue<>(Comparator.comparingInt(huffBT -> huffBT.getData()
                                                                            .getFrequency()));

        /* crear y guardar en la PQ un BinaryTree con los datos del map */
        map.forEach((k, v) -> {
            pq.offer(new BinaryTree<>(
                    new HuffmanInfo(Character.toString(k), v)));
        });

        BinaryTree<HuffmanInfo> huffBT = null;
        while (true) {
            final int pqSize = pq.size();
            if (pqSize < 2) {
                break;
            }

            final BinaryTree<HuffmanInfo> rightTree = pq.poll();
            final BinaryTree<HuffmanInfo> leftTree = pq.poll();

            /* crear un nuevo árbol contieniendo la unión sus 2 hijos */
            huffBT = new BinaryTree<>(new HuffmanInfo(
                    huffStrCat(rightTree, leftTree),
                    huffFreqSum(rightTree, leftTree)));

            huffBT.setLeft(leftTree);
            huffBT.setRight(rightTree);

            pq.offer(huffBT);
        }

        return huffBT;
    }

    private static String huffStrCat(final BinaryTree<HuffmanInfo> huffBT1,
                              final BinaryTree<HuffmanInfo> huffBT2) {
        return huffBT1.getData().getText() + huffBT2.getData().getText();
    }

    private static int huffFreqSum(final BinaryTree<HuffmanInfo> huffBT1,
                            final BinaryTree<HuffmanInfo> huffBT2) {
        return huffBT1.getData().getFrequency()
               + huffBT2.getData().getFrequency();
    }
}
