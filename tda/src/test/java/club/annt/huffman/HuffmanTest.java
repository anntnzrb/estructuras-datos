package club.annt.huffman;

import club.annt.tree.BinaryTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings({"AssertWithoutMessage", "DuplicateStringLiteralInspection"
        , "CollectionWithoutInitialCapacity"})
class HuffmanTest {
    private Map<Character, Integer> map;
    private BinaryTree<HuffmanInfo> huffBT;
    private Map<Character, String>  map1;
    private Map<String, Character>  map2;
    private List<Character>         chars;

    @BeforeEach
    void setUp() {
        map = Huffman.getFrequencies("LAHOOOBAOCHHLCDA");
        huffBT = Huffman.buildHuffmanTree(map);
        map1 = new LinkedHashMap<>();
        map2 = new LinkedHashMap<>();
        chars = List.of('H', 'O', 'L', 'A');
    }

    @Test
    void getFrecuencies() {
        assertEquals("{A=3, B=1, C=2, D=1}",
                     Huffman.getFrequencies("ABACCDA").toString());

        assertEquals("{S=2, T=2, R=2, U=2, C=1, E=1}",
                     Huffman.getFrequencies("STRUCTURES").toString());
    }

    @Test
    void buildHuffmanTree() {
        final BinaryTree<HuffmanInfo> huffBT = Huffman.buildHuffmanTree(map);

        System.out.println(huffBT.getData().getText());
        System.out.println(huffBT.getLeft().getData().getText());
        System.out.println(huffBT.getRight().getData().getText());
    }

    @Test
    void encode() {
        Huffman.getHuffmanCodes(chars, huffBT, map1, map2);
        assertEquals("0010110111", Huffman.encode("HOLA", map1));
        assertEquals("{H=00, O=10, L=110, A=111}", map1.toString());
    }

    @Test
    void decode() {
        Huffman.getHuffmanCodes(chars, huffBT, map1, map2);
        assertEquals("HOLA", Huffman.decode("0010110111", map2));
        assertEquals("{00=H, 10=O, 110=L, 111=A}", map2.toString());
    }

    @Test
    void fileRead() {
        try (final BufferedReader br =
                     new BufferedReader(
                             new InputStreamReader(Huffman.class.getResourceAsStream(
                                     "lol.txt")))) {
        } catch (final IOException ioEx) {
            ioEx.printStackTrace();
        }
    }
}