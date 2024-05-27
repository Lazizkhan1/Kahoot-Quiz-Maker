
```markdown
# Test Generator

This project is a test generator that reads a list of questions, options, and answers from a text file and writes them to an Excel file using Apache POI. It also provides functionality to update an existing Excel file and write data to a specific cell.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java 8 or higher
- Gradle

### Installing
```
1. Clone the repository

```bash
git clone https://github.com/Lazizkhan1/test-generator.git
```
2. Navigate into the cloned repository
```bash
cd test-generator
```

3. Build the project using Gradle
```bash
gradle jar
```
4. Run the project
```bash
java -jar build/libs/test-generator.jar <input-file> 
```

## Usage

The main class of the project is `Generator`. It reads a list of tests from a text file, where each test consists of a question, a list of options, and an answer. The tests are then written to an Excel file.

The `Test` class represents a test with a question, a list of options, an answer, and a time limit. It provides a method `toRow` to convert a `Test` object to a `Row` in an Excel sheet.

The `Generator` class also provides a method `writeToB9` to write data to the B column and 9th row in an Excel file.

## Built With

- [Java](https://www.java.com/)
- [Apache POI](https://poi.apache.org/)
- [Gradle](https://gradle.org/)

## Authors

- Lazizkhan1
