package unisa.dse.a2.students;

/**
 * Represents a trade order in the system.
 * Each trade is associated with a broker, company code, and share quantity.
 */
public class Trade implements Comparable<Trade> {

    /* Don't modify the tradeId or created values, as they're used to simplify the tests */
    private long tradeId = -1;
    private long created;

    private String listedCompanyCode;
    private int shareQuantity;
    private StockBroker broker;

    /**
     * @return The timestamp of trade creation
     */
    public long getCreated() {
        return created;
    }

    /**
     * @return The company's stock code
     */
    public String getCompanyCode() {
        return listedCompanyCode;
    }

    /**
     * @return The quantity of shares in the trade
     */
    public int getShareQuantity() {
        return shareQuantity;
    }

    /**
     * @return The broker processing this trade
     */
    public StockBroker getStockBroker() {
        return broker;
    }

    /***
     * Do not modify this constructor, it is used for testing purposes only
     */
    public Trade(StockBroker broker, int id) {
        created = System.nanoTime();
        tradeId = id;
        try { Thread.sleep(100); } catch (Exception x) {}
    }

    /**
     * Constructs a new Trade object with broker, company code, and share quantity
     */
    public Trade(StockBroker broker, String listedCompanyCode, int shareQuantity) {
        created = System.nanoTime();
        tradeId = System.nanoTime();
        try { Thread.sleep(100); } catch (Exception x) {}

        this.broker = broker;
        this.listedCompanyCode = listedCompanyCode;
        this.shareQuantity = shareQuantity;
    }

    /**
     * Compares this trade to another trade.
     * Priority logic:
     * - If both trades are on their brokers' watchlists → 0
     * - If only this trade is on its broker's watchlist → 1
     * - If only the other trade is on its broker's watchlist → -1
     * - Else compare by creation time
     */
    public int compareTo(Trade t) {
        boolean thisOnWatchlist = broker.getWatchlist().contains(this.listedCompanyCode);
        boolean otherOnWatchlist = t.broker.getWatchlist().contains(t.listedCompanyCode);

        if (thisOnWatchlist && otherOnWatchlist) {
            return 0;
        } else if (thisOnWatchlist) {
            return 1;
        } else if (otherOnWatchlist) {
            return -1;
        } else {
            return Long.compare(this.created, t.created);
        }
    }

    /***
     * Do not modify this toString, it is used for testing purposes
     */
    @Override
    public String toString() {
        return "" + tradeId;
    }

    /***
     * Do not modify this equals, it is used for testing purposes
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Trade other = (Trade) obj;
        return tradeId == other.tradeId;
    }
}
