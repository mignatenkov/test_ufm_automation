import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.testng.Assert;
import pojo.Output;
import ru.yandex.qatools.allure.annotations.Step;

public class MongoConnector {
    private MongoClient mongo;
    private String databaseName;
    private String collectionName;

    MongoConnector() {
        mongo = new MongoClient("localhost", 27017);
        databaseName = "ufm";
        collectionName = "any";
    }

    @Step
    private Document getEntryByClientId(long clientId) {
        MongoCollection collection = mongo.getDatabase(databaseName).getCollection(collectionName);
        return (Document) collection.find(Filters.eq("clientId", clientId)).first();
    }

    @Step
    Output getOutputFromDB(long clientId) {
        Document doc = getEntryByClientId(clientId);
        Assert.assertNotNull(doc, "Can't find enrty in MongoDB with required clientId: " + clientId + ".");
        return new Gson().fromJson(doc.toJson(), Output.class);
    }
}
