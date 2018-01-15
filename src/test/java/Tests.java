import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;

public class Tests{

    @Features("Comparison of Input and Output files")
    @Description("Read Input file, generate output from it, and compare result to file from output folder")
    @Test
    public void compareFiles() {
        String inputFilePath = "src/main/resources/input/11199.json";
        String outputFilePath = "src/main/resources/output/11199.json";
        new Comparator().compareFileToFile(inputFilePath, outputFilePath);
    }

    @Features("Comparison of Input and Output files")
    @Description("Read Input file, generate output from it, and compare result to file from output folder")
    @Test
    public void compareBigFiles() {
        String inputFilePath = "src/main/resources/input/999.json";
        String outputFilePath = "src/main/resources/output/999.json";
        new Comparator().compareFileToFile(inputFilePath, outputFilePath);
    }

    @Features("Comparison of Input file and Output from DB")
    @Description("Read Input file, generate output from it, and compare result to result from MongoDB")
    @Test
    public void compareFileAndDB() {
        String inputFilePath = "src/main/resources/input/11199.json";
        long dbClientId = 11199;
        new Comparator().compareFileToDb(inputFilePath, dbClientId);
    }

    @Features("Comparison of Input and Output files")
    @Description("Read Input file, generate output from it, and compare result to file from output folder")
    @Test//(expectedExceptions = AssertionError.class)
    public void wrongOutputFileName() {
        String inputFilePath = "src/main/resources/input/11199.json";
        String outputFilePath = "src/main/resources/output/1200.json";
        new Comparator().compareFileToFile(inputFilePath, outputFilePath);
    }

    @Features("Comparison of Input and Output files")
    @Description("Read Input file, generate output from it, and compare result to file from output folder")
    @Test//(expectedExceptions = AssertionError.class)
    public void wrongInputFileName() {
        String inputFilePath = "src/main/resources/input/1200.json";
        String outputFilePath = "src/main/resources/output/11199.json";
        new Comparator().compareFileToFile(inputFilePath, outputFilePath);
    }

    @Features("Comparison of Input file and Output from DB")
    @Description("Read Input file, generate output from it, and compare result to result from MongoDB")
    @Test//(expectedExceptions = AssertionError.class)
    public void wrongEntryInDB() { //wrong clientId and spentTotal
        String inputFilePath = "src/main/resources/input/11199.json";
        long dbClientId = 2515;
        new Comparator().compareFileToDb(inputFilePath, dbClientId);
    }

    @Features("Comparison of Input file and Output from DB")
    @Description("Read Input file, generate output from it, and compare result to result from MongoDB")
    @Test//(expectedExceptions = AssertionError.class)
    public void noEntryInDB() {
        String inputFilePath = "src/main/resources/input/11199.json";
        long dbClientId = 11002;
        new Comparator().compareFileToDb(inputFilePath, dbClientId);
    }
}