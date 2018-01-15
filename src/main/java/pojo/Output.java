package pojo;

public class Output {
    private long clientId;
    private long spentTotal;
    private boolean isBig;

    public Output(long clientId, long spentTotal, boolean isBig) {
        this.clientId = clientId;
        this.spentTotal = spentTotal;
        this.isBig = isBig;
    }

    public long getClientId() {
        return clientId;
    }

    public long getSpentTotal() {
        return spentTotal;
    }

    public boolean getIsBig() {
        return isBig;
    }

    @Override
    public String toString() {
        return "{" +
                "\"clientId\":" + clientId +
                ", \"spentTotal\":" + spentTotal +
                ", \"isBig\":" + isBig +
                '}';
    }
}
