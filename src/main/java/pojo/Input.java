package pojo;

import java.util.List;

public class Input {
    private long clientId;
    private List<Subscriber> subscribers;

    public long getClientId() {
        return clientId;
    }

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public void setSubscribers(List<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }

    public void addSubscriber(Subscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    @Override
    public String toString() {
        return "{" +
                "\"clientId\":" + clientId +
                ", \"subscribers\":" + subscribers +
                '}';
    }
}
