package club.annt.taller.model;

/**
 * @author Gonzalo Gabriel Méndez (gmendez@espol.edu.ec)
 */
public class Pair {
    char character;
    int  frequency;

    public Pair(final char character, final int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(final char character) {
        this.character = character;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(final int frequency) {
        this.frequency = frequency;
    }
}