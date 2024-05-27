import models.Test;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Generator {
    private static final int offset = 8;
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
                temp.setOptions(word.substring(1));
                temp.setAnswers(optionIndex);
            }
            else {
                if (queston)
                    temp.setQuestion(word);
                else
                    temp.getOptions().add(word);
            }

        }

        Workbook workbook = getSheets(tests);

        workbook.close();
    }

    private static Workbook getSheets(List<Test> tests) throws IOException {
        FileInputStream fileIn = new FileInputStream("src/main/resources/Template.xlsx");
        Workbook workbook = new XSSFWorkbook(fileIn);
        Sheet sheet = workbook.getSheetAt(0);
        for (int i = 0; i < tests.size(); i++) {
            Test test = tests.get(i);
            Row row = sheet.createRow(i + offset); // +1 because the first row is the header row
            test.toRow(row);
        }

        // Write the workbook to a file
        try (FileOutputStream fileOut = new FileOutputStream("Tests.xlsx")) {
            workbook.write(fileOut);
        }
        return workbook;
    }
}
