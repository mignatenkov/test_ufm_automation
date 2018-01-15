package pojo;

public class Subscriber {
    private long id;
    private long spent;

    public long getId() {
        return id;
    }

    public long getSpent() {
        return spent;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSpent(long spent) {
        this.spent = spent;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"spent\":" + spent +
                '}';
    }
}
