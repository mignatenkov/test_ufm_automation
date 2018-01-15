import org.testng.asserts.SoftAssert;
import pojo.Output;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import java.io.File;

public class Comparator {
    private SoftAssert softAssert;

    Comparator() {
        softAssert = new SoftAssert();
    }

    @Step
    private void compareSpentTotal(Output testOutput, Output output) {
        String message = "spentTotal value is not matching expected:";
        softAssert.assertEquals(testOutput.getSpentTotal(), output.getSpentTotal(), message);
    }

    @Step
    private void compareIsBig(Output testOutput, Output output) {
        String message = "isBig value is not matching expected:";
        softAssert.assertEquals(testOutput.getIsBig(), output.getIsBig(), message);
    }

    @Step
    @Title("some title")
    private void compareClientId(Output testOutput, Output output) {
        final String message = "clientId value is not matching expected:";
        softAssert.assertEquals(testOutput.getClientId(), output.getClientId(), message);
    }

    @Step
    private void compareFileNameToClientId(String filePath, Output output) {
        String message = "Name of file isn't matching clientId:";
        String name = new File(filePath).getName();
        String clientId = String.valueOf(output.getClientId()).concat(".json");
        softAssert.assertEquals(name, clientId, message);
    }

    private void compareOutputToOutput(Output testOutput, Output output) {
        compareClientId(testOutput, output);
        compareSpentTotal(testOutput, output);
        compareIsBig(testOutput, output);
    }

    private void printFileToFile(Output testOutput, Output appOutput){
        System.out.println("==============start==============");
        System.out.println("Output, generated by test from input file: ");
        System.out.println(testOutput.toString());
        System.out.println("Output, read from output file: ");
        System.out.println(appOutput.toString());
        System.out.println("===============end===============");
    }

    private void printFileToDb(Output testOutput, Output appOutput){
        System.out.println("==============start==============");
        System.out.println("Output, generated by test from input file: ");
        System.out.println(testOutput.toString());
        System.out.println("Output, read from MongoDB: ");
        System.out.println(appOutput.toString());
        System.out.println("===============end===============");
    }

    public void compareFileToFile(String inputFilePath, String outputFilePath) {
        Output testOutput = new Generator().generateOutputFromInputFile(inputFilePath);
        Output appOutput = new Generator().readOutputFromFile(outputFilePath);
        compareOutputToOutput(testOutput, appOutput);
        compareFileNameToClientId(inputFilePath, testOutput);
        compareFileNameToClientId(outputFilePath, testOutput);
        printFileToFile(testOutput, appOutput);
        softAssert.assertAll();
    }

    public void compareFileToDb(String inputFilePath, long dbClientId) {
        Output testOutput = new Generator().generateOutputFromInputFile(inputFilePath);
        Output dbAppOutput = new MongoConnector().getOutputFromDB(dbClientId);
        compareOutputToOutput(testOutput, dbAppOutput);
        printFileToDb(testOutput, dbAppOutput);
        softAssert.assertAll();
    }
}