public class Client {
    public long clientId;
    public Subscribers[] subscribers;

    public Client(long clientId, Subscribers[] subscribers){
        this.clientId = clientId;
        this.subscribers = subscribers;
    }
}