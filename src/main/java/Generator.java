import com.google.gson.Gson;
import pojo.Input;
import pojo.Input.Subscriber;
import pojo.Output;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;

public class Generator {

    @Step
    private long countSpent(Input input) {
        long spentTotal = 0;
        for (Subscriber subscriber : input.getSubscribers()) {
            spentTotal += subscriber.getSpent();
        }
        return spentTotal;
    }

    @Step
    private boolean checkIsBig(Input input) {
        boolean isBig = false;
        if (input.getSubscribers().size() >= 100) {
            isBig = true;
        }
        return isBig;
    }

    @Step
    private Output generateOutput(Input input) {
        return new Output(input.getClientId(), countSpent(input), checkIsBig(input));
    }

    @Step
    private BufferedReader readFile(String pathToFile) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(
                    new FileReader(pathToFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return br;
    }

    @Step
    Output readOutputFromFile(String pathToFile) {
        return new Gson().fromJson(readFile(pathToFile), Output.class);
    }

    @Step
    Output generateOutputFromInputFile(String pathToFile) {
        return generateOutput(new Gson().fromJson(readFile(pathToFile), Input.class));
    }
}
