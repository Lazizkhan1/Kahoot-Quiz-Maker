import models.Test;

import java.io.File;
import java.io.IOException;

import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Generator {
    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        if (!file.canRead())
            throw new IllegalArgumentException("File not found or cannot be read");

        List<String> words = Files.readAllLines(file.toPath());
        List<Test> tests = new ArrayList<>();
        Test temp = new Test();
        int optionIndex = 0;
        boolean queston = true;

        for (String word : words) {

            if (word.charAt(0) == '+') {
                optionIndex = 0;
                queston = true;
                tests.add(new Test(temp));
                temp = new Test();
            } else if (word.charAt(0) == '=') {
                queston = false;
                optionIndex++;
            } else if (word.charAt(0) == '#') {
                temp.getAnswers().add(optionIndex);
                temp.getOptions().add(word.substring(1));
            }
            else {
                if (queston)
                    temp.setQuestion(word);
                else
                    temp.getOptions().add(word);

            }

        }
        System.out.println(tests);
    }
}
