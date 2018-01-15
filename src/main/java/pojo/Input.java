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

    @Override
    public String toString() {
        return "{" +
                "\"clientId\":" + clientId +
                ", \"subscribers\":" + subscribers +
                '}';
    }

    public class Subscriber {
        private long id;
        private long spent;

        public long getId() {
            return id;
        }

        public long getSpent() {
            return spent;
        }

        @Override
        public String toString() {
            return "{" +
                    "\"id\":" + id +
                    ", \"spent\":" + spent +
                    '}';
        }
    }
}
