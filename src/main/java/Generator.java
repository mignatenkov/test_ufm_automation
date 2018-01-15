import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pojo.Input;
import pojo.Input.Subscriber;
import pojo.Output;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.*;
import java.nio.file.Path;
java.util.Random

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

    @Step
    void generateJson(String pathToInput, int clientCount, int minSubscribers, int maxSubscribers) throws IOException {
        Random random = new Random();
        for(int i=0; i<clientCount; i++) {
            Writer writer = new OutputStreamWriter(new FileOutputStream(String.join(pathToInput, client.clientId + ".json")), "UTF-8");
            Gson gson = new GsonBuilder().create();

            Subscribers[] subscribers = new Subscribers[randomNumber(minSubscribers, maxSubscribers, random)];
            for(int j=0; j<subscribers.length; j++){
                subscribers[j] = new Subscribers(random.nextLong(), random.nextLong());
            }
            Client client = new Client(random.nextLong(), subscribers);

            gson.toJson(client);
            writer.close();
        }
    }

    private int randomNumber(int minSubscribers, int maxSubscribers, Random random){
        return (int)((long)(((long)minSubscribers - (long)maxSubscribers + 1) * random.nextDouble()) + minSubscribers);
    }
}
