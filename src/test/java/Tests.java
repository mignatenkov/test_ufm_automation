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
        Generator generator = new Generator();
        generator.generateRandomInputFile(10011, 20);
        generator.generateRandomInputFile(10012, 1000);
        generator.generateRandomInputFile(10001, 20);
        generator.generateRandomInputFile(10002, 99);
        generator.generateRandomInputFile(10003, 100);
        generator.generateRandomInputFile(10004, 101);
        generator.generateRandomInputFile(10005, 102);
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
        Comparator comparator = new Comparator();
        comparator.compareFileToFile(processedFolder + "10001.json", outputFolder + "10001.json");
        comparator.compareFileToFile(processedFolder + "10002.json", outputFolder + "10002.json");
        comparator.compareFileToFile(processedFolder + "10003.json", outputFolder + "10003.json");
        comparator.compareFileToFile(processedFolder + "10004.json", outputFolder + "10004.json");
        comparator.compareFileToFile(processedFolder + "10005.json", outputFolder + "10005.json");
    }

    @Features("Compare five input files to expected output in DB")
    @Test
    public void fiveFilesDB() {
        Comparator comparator = new Comparator();
        comparator.compareFileToDb(processedFolder + "10001.json", 10001);
        comparator.compareFileToDb(processedFolder + "10002.json", 10002);
        comparator.compareFileToDb(processedFolder + "10003.json", 10003);
        comparator.compareFileToDb(processedFolder + "10004.json", 10004);
        comparator.compareFileToDb(processedFolder + "10005.json", 10005);
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

    @Features("Calculate the processing time of small files")
    @Test
    public void smallFilesTimer() {
        new Comparator().fileWaiter(90011, 1);
        new Comparator().fileWaiter(90012, 10);
        new Comparator().fileWaiter(90013, 50);
        new Comparator().fileWaiter(90014, 100);
        new Comparator().fileWaiter(90015, 150);
        new Comparator().fileWaiter(90016, 200);
    }

    @Features("Calculate the processing time of big files")
    @Test
    public void bigFilesTimer() {
        new Comparator().fileWaiter(90021, 500);
        new Comparator().fileWaiter(90022, 600);
        new Comparator().fileWaiter(90023, 800);
        new Comparator().fileWaiter(90024, 900);
        new Comparator().fileWaiter(90025, 950);
        new Comparator().fileWaiter(90026, 1000);
    }
}