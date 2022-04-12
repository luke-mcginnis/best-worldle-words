import java.util.ArrayList;
import java.util.Locale;

public class Answer {
    public static final int LENGTH = 5;
    private final String word;

    final char correct = 'C';
    final char present = 'P';
    final char absent = 'A';

    public Answer(String word) {
        if (word.length() != LENGTH) {
            throw new IllegalArgumentException("Word must be 5 characters");
        }
        this.word = word.toLowerCase(Locale.ROOT);
    }

    public char[] guessWord(String guess) {
        ArrayList<Character> matches = new ArrayList<>();
        char[] result = new char[LENGTH];

        for (int i = 0; i < LENGTH; i++) {
            if (guess.charAt(i) == word.charAt(i)) {
                result[i] = correct;
                matches.add(guess.charAt(i));
            }
        }

        for (int i = 0; i < LENGTH; i++) {
            if (result[i] != 0) {
                continue;
            }
            for (int j = 0; j < LENGTH; j++) {
                if (guess.charAt(i) == word.charAt(j) && !matches.contains(guess.charAt(i))) {
                    result[i] = present;
                    matches.add(guess.charAt(i));
                }
            }
        }

        for (int i = 0; i < LENGTH; i++) {
            if (result[i] == 0) {
                result[i] = absent;
            }
        }

        return result;
    }

    public int guessScore(String guess) {
        char[] result = guessWord(guess);
        int score = 0;
        for (int i = 0; i < LENGTH; i++) {
            if (result[i] != absent) {
                score++;
            }
        }
        return score;
    }

    public String toString() {
        return word;
    }

    public String getWord() {
        return word;
    }
}
