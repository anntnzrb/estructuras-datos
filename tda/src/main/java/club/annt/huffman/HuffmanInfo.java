package club.annt.huffman;

public final class HuffmanInfo {
    private final String text;
    private final int    frequency;

    public HuffmanInfo(final String text, final int frequency) {
        this.text = text;
        this.frequency = frequency;
    }

    public String getText() {
        return text;
    }

    public int getFrequency() {
        return frequency;
    }
}
