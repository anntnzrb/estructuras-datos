package club.annt;

import club.annt.huffman.HuffmanInfo;
import club.annt.tree.BinaryTree;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static club.annt.huffman.Huffman.*;
import static club.annt.util.Util.getFileAsStr;
import static club.annt.util.Util.mkFile;

public class MainApp {
    public static void main(final String... args) throws IOException {
        /* obtener la data del archivo en un String */
        final String fileAsStr = getFileAsStr("assets/sample.txt");

        /* generar tabla de frecuencias */
        final Map<Character, Integer> freqMap = getFrequencies(fileAsStr);
        /* construir árbol de codificación de Huffman */
        final BinaryTree<HuffmanInfo> huffBT = buildHuffmanTree(freqMap);

        /* lista de caracteres únicos del archivo */
        final List<Character> chars = freqMap.keySet()
                                             .stream()
                                             .collect(Collectors.toUnmodifiableList());

        /* generar los códigos correspondientes */
        final Map<Character, String> map1 = new LinkedHashMap<>();
        final Map<String, Character> map2 = new LinkedHashMap<>();
        getHuffmanCodes(chars, huffBT, map1, map2);

        /* codificar data del archivo y crear nuevo archivo codificado */
        final String encodedFileData = encode(fileAsStr, map1);
        mkFile("assets/sample-encoded.txt", encodedFileData);

        /* decodificar data del archivo y crear nuevo archivo decodificado */
        final String decodedFileData = decode(encodedFileData, map2);
        mkFile("assets/sample-decoded.txt", decodedFileData);
    }
}