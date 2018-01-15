import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;

@Description("Read Input file, generate output from it, and compare result to file from output folder and record in DB")
public class Tests {
    private static final String processedFolder = "ufm_dev/processed/";
    private static final String outputFolder = "ufm_dev/output/";

    @BeforeClass
    public void beforeClass() throws InterruptedException {
        new Generator().generateRandomInputFile(10011, 20);
        new Generator().generateRandomInputFile(10012, 1000);
        new Generator().generateRandomInputFile(10001, 20);
        new Generator().generateRandomInputFile(10002, 99);
        new Generator().generateRandomInputFile(10003, 100);
        new Generator().generateRandomInputFile(10004, 101);
        new Generator().generateRandomInputFile(10005, 102);
        Thread.sleep(5000); //wait until app will process files
    }

    @Features("Compare input file to expected output result")
    @Test
    public void oneFile() {
        new Comparator().compareFileToFile(processedFolder + "10011.json", outputFolder + "10011.json");
    }

    @Features("Compare input file to expected output in DB")
    @Test
    public void oneFileDB() {
        new Comparator().compareFileToDb(processedFolder + "10011.json", 10011);
    }

    @Features("Compare five files to expected output results")
    @Test
    public void fiveFiles() {
        new Comparator().compareFileToFile(processedFolder + "10001.json", outputFolder + "10001.json");
        new Comparator().compareFileToFile(processedFolder + "10002.json", outputFolder + "10002.json");
        new Comparator().compareFileToFile(processedFolder + "10003.json", outputFolder + "10003.json");
        new Comparator().compareFileToFile(processedFolder + "10004.json", outputFolder + "10004.json");
        new Comparator().compareFileToFile(processedFolder + "10005.json", outputFolder + "10005.json");
    }

    @Features("Compare five input files to expected output in DB")
    @Test
    public void fiveFilesDB() {
        new Comparator().compareFileToDb(processedFolder + "10001.json", 10001);
        new Comparator().compareFileToDb(processedFolder + "10002.json", 10002);
        new Comparator().compareFileToDb(processedFolder + "10003.json", 10003);
        new Comparator().compareFileToDb(processedFolder + "10004.json", 10004);
        new Comparator().compareFileToDb(processedFolder + "10005.json", 10005);
    }

    @Features("Compare big input file to expected output result")
    @Test
    public void oneBigFile() {
        new Comparator().compareFileToFile(processedFolder + "10012.json", outputFolder + "10012.json");
    }

    @Features("Compare big input file to expected output in DB")
    @Test
    public void oneBigFileDB() {
        new Comparator().compareFileToDb(processedFolder + "10012.json", 10012);
    }
}