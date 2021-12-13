package club.annt.huffman;

@SuppressWarnings("ComparableImplementedButEqualsNotOverridden")
public final class HuffmanInfo implements Comparable<HuffmanInfo> {
    private String text;
    private int    frequency;

    /* constructor */
    private HuffmanInfo() {}

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

    @Override
    public String toString() {
        return "{" + text + ":" + frequency + "}";
    }

    @SuppressWarnings({"SubtractionInCompareTo",
            "CallToSuspiciousStringMethod"})
    @Override
    public int compareTo(final HuffmanInfo otherHuff) {
        return frequency - otherHuff.frequency == 0
               ? text.compareTo(otherHuff.text)
               : frequency - otherHuff.frequency;
    }

}
