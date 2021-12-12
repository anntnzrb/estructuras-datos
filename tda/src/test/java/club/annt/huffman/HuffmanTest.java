package club.annt.huffman;

import club.annt.tree.BinaryTree;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("AssertWithoutMessage")
class HuffmanTest {
    @Test
    void getFrecuencies() {
        assertEquals("{A=3, B=1, C=2, D=1}",
                     Huffman.getFrecuencies("ABACCDA").toString());

        assertEquals("{S=2, T=2, R=2, U=2, C=1, E=1}",
                     Huffman.getFrecuencies("STRUCTURES").toString());
    }

    @Test
    void buildHuffmanTree() {
        final Map<Character, Integer> map = Huffman.getFrecuencies("ABACCDA");
        final BinaryTree<HuffmanInfo> huffBT = Huffman.buildHuffmanTree(map);

        System.out.println(huffBT.getData().getText());
        System.out.println(huffBT.getLeft().getData().getText());
        System.out.println(huffBT.getRight().getData().getText());
    }

    @Test
    void getHuffmanCodes() {
        final Map<Character, String> map1 = new HashMap<>();
        final Map<String, Character> map2 = new HashMap<>();
        final List<Character> charList = List.of('H', 'O', 'L', 'A');
        final BinaryTree<HuffmanInfo> huffBT =
                Huffman.buildHuffmanTree(Huffman.getFrecuencies(
                        "ABACCDA"));

        Huffman.getHuffmanCodes(charList, huffBT, map1, map2);

        //System.out.println(map1);
        //System.out.println(map2);
    }

    @Test
    void fileRead() {
        try (final BufferedReader br =
                     new BufferedReader(
                             new InputStreamReader(Huffman.class.getResourceAsStream("lol.txt")))) {
        } catch (final IOException ioEx) {
            ioEx.printStackTrace();
        }
    }
}