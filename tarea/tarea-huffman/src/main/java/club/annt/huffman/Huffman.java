package club.annt.huffman;

import club.annt.tree.BinaryTree;

import java.util.*;

public final class Huffman {
    public static Map<Character, Integer> getFrequencies(final String str) {
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

    public static BinaryTree<HuffmanInfo> buildHuffmanTree(
            final Map<Character, Integer> map) {
        /* PQ con frecuencias del contenido de cada raíz (ascendentemente) */
        final Queue<BinaryTree<HuffmanInfo>> pq =
                new PriorityQueue<>(Comparator.comparing(BinaryTree::getData));

        /* crear y guardar en la PQ un BinaryTree con los datos del map */
        map.forEach((k, v) -> {
            pq.offer(new BinaryTree<>(
                    new HuffmanInfo(Character.toString(k), v)));
        });

        while (true) {
            final int isQueueEmpty = pq.size();
            if (isQueueEmpty <= 1) {
                break;
            }

            final BinaryTree<HuffmanInfo> leftTree = pq.poll();
            final BinaryTree<HuffmanInfo> rightTree = pq.poll();

            /* crear un nuevo árbol contieniendo la unión sus 2 hijos */
            final BinaryTree<HuffmanInfo> huffBT =
                    new BinaryTree<>(new HuffmanInfo(
                            huffStrCat(leftTree, rightTree),
                            huffFreqSum(leftTree, rightTree)));

            huffBT.setLeft(leftTree);
            huffBT.setRight(rightTree);

            pq.offer(huffBT);
        }

        return pq.poll();
    }

    public static void getHuffmanCodes(final Iterable<Character> charList,
                                       final BinaryTree<HuffmanInfo> huffBT,
                                       final Map<Character, String> map1,
                                       final Map<String, Character> map2) {
        final Map<Character, String> map3 = new HashMap<>();
        huffmanCodes(huffBT, map3, "");

        charList.forEach(ch -> {
            map1.put(ch, map3.get(ch));
            map2.put(map3.get(ch), ch);
        });
    }

    public static String encode(final String input,
                                final Map<Character, String> map) {
        final StringBuilder strBld = new StringBuilder();
        for (final char ch : input.toCharArray()) {
            strBld.append(map.get(ch));
        }

        return strBld.toString();
    }

    public static String decode(final String input,
                                final Map<String, Character> map) {
        final StringBuilder decoded = new StringBuilder();
        final StringBuilder strBld2 = new StringBuilder();

        for (final char ch : input.toCharArray()) {
            strBld2.append(ch);
            if (map.containsKey(strBld2.toString())) {
                decoded.append(map.get(strBld2.toString()));
                strBld2.setLength(0);
            }
        }

        return decoded.toString();
    }

    /* métodos auxiliares */
    private static void huffmanCodes(final BinaryTree<HuffmanInfo> huffBT,
                                     final Map<Character, String> map,
                                     final String code) {
        if (huffBT.isLeaf()) {
            map.put(huffBT.getData().getText().charAt(0), code);
            return;
        }

        huffmanCodes(huffBT.getLeft(), map, code + "0");
        huffmanCodes(huffBT.getRight(), map, code + "1");
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