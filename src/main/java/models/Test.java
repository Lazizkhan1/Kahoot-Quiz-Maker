package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.Row;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Test {
    private static final int startRow = 1;
    private static final int startCol = 1;
    private String question;
    private List<String> options = new LinkedList<>();
    private int answer;
    private int timeLimit = 20;

    public void setQuestion(String question) {
        if (question.length() > 120) {
            throw new IllegalArgumentException("Question should be less than 120 characters");
        }
        this.question = question;
    }

    public Test(Test test) {
        this.question = test.question;
        this.options = test.options;
        this.answer = test.answer;
        this.timeLimit = test.timeLimit;
    }

    public void setOptions(String option) {
        if (option.length() > 75)
            throw new IllegalArgumentException("Options should be less than 75 characters");

        options.add(option);
    }

    public void setAnswers(int answer) {

            if (answer < 1 || answer > 4) {
                throw new IllegalArgumentException("Answers should be in 1-4 range");
            }
            if (options.get(answer - 1) == null) {
                throw new IllegalArgumentException("Answer index at options should not be null");
            }

        this.answer = answer;
    }

    public void toRow(Row row) {
        row.createCell(startRow).setCellValue(this.question);
        row.createCell(startRow + 1).setCellValue(options.isEmpty() ? "" : options.get(0));
        row.createCell(startRow + 2).setCellValue(options.size() > 1 ? options.get(1) : "");
        row.createCell(startRow + 3).setCellValue(options.size() > 2 ? options.get(2) : "");
        row.createCell(startRow + 4).setCellValue(options.size() > 3 ? options.get(3) : "");
        row.createCell(startRow + 5).setCellValue(this.timeLimit);
        row.createCell(startRow + 6).setCellValue(this.answer);
    }
}