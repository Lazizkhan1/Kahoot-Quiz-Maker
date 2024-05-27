package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Test {
    private String question;
    private List<String> options = new ArrayList<>();
    private List<Integer> answers = new ArrayList<>();
    private int timeLimit;

    public void setQuestion(String question) {
        if (question.length() > 120) {
            throw new IllegalArgumentException("Question should be less than 120 characters");
        }
        this.question = question;
    }

    public Test(Test test) {
        this.question = test.question;
        this.options = test.options;
        this.answers = test.answers;
        this.timeLimit = test.timeLimit;
    }

    public void setOptions(List<String> options) {
        if (options.size() > 4) {
            throw new IllegalArgumentException("Options size should be maximum 4");
        }
        for (String option : options) {
            if (option.length() > 75) {
                throw new IllegalArgumentException("Each option should be maximum 75 characters");
            }
        }
        this.options = options;
    }

    public void setAnswers(List<Integer> answers) {
        for (Integer answer : answers) {
            if (answer < 1 || answer > 4) {
                throw new IllegalArgumentException("Answers should be in 1-4 range");
            }
            if (options.get(answer - 1) == null) {
                throw new IllegalArgumentException("Answer index at options should not be null");
            }
        }
        this.answers = answers;
    }

    public void setTimeLimit(int timeLimit) {
        List<Integer> validTimeLimits = Arrays.asList(5, 10, 20, 30, 60, 90, 120, 240);
        if (!validTimeLimits.contains(timeLimit)) {
            throw new IllegalArgumentException("Time limit should be 5, 10, 20, 30, 60, 90, 120, or 240");
        }
        this.timeLimit = timeLimit;
    }
}