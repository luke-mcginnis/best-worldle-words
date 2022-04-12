import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class BestWord {
    public static void main(String[] args) throws IOException {
        List<String> answers = PathToList("answers.txt");
        List<String> words = PathToList("words.txt");

        FileWriter csvWriter = new FileWriter("result.csv");
        csvWriter.write("Word,Average Matches\n");

        for (String word : words) {
            int totalScore = 0;
            for (String answer : answers) {
                Answer currentAnswer = new Answer(answer);
                totalScore += currentAnswer.guessScore(word);
            }
            float average = totalScore/(float)answers.size();
            System.out.println(word + "\t" + average);
            csvWriter.write(word + "," + average + "\n");
        }
        csvWriter.flush();
        csvWriter.close();
    }


    private static List<String> PathToList(String path) {
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            return List.of();
        }
    }
}
