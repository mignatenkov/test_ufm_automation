import com.google.gson.Gson;
import org.testng.Assert;
import pojo.Input;
import pojo.Output;
import pojo.Subscriber;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

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
        if (input.getSubscribers().size() > 100) {
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
        Assert.assertNotNull(br, "Can't read file: " + pathToFile);
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

    @Step
    public void generateRandomInputFile(long clientId, int subscribersCount) {
        final String inputFolder = "ufm_dev/input/";
        Input input = new Input();
        input.setClientId(clientId);
        input.setSubscribers(new ArrayList<>());
        for (int i = 0; i < subscribersCount; i++) {
            Subscriber subscriber = new Subscriber();
            subscriber.setId(ThreadLocalRandom.current().nextLong(1, 999999));
            subscriber.setSpent(ThreadLocalRandom.current().nextLong(1, 999999));
            input.addSubscriber(subscriber);
        }
        try {
            Files.write(Paths.get(inputFolder + clientId + ".json"), input.toString().getBytes(Charset.forName("UTF-8")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
