package club.annt.huffman;

import org.junit.jupiter.api.Test;

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
        final var map = Huffman.getFrecuencies("ABACCDA");

        var bt = Huffman.buildHuffmanTree(map);
        System.out.println(bt.getData().getText());
        System.out.println(bt.getLeft().getData().getText());
        System.out.println(bt.getRight().getData().getText());
    }
}