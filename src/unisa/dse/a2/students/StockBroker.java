package unisa.dse.a2.students;

import java.util.PriorityQueue;

/**
 * Represents a stock broker who can watch companies and place/manage trade orders.
 */
public class StockBroker {

    /** Queue of pending trades to be processed, ordered by trade priority */
    private PriorityQueue<Trade> pendingTrades = new PriorityQueue<Trade>();

    /** List of company codes this broker is actively watching */
    private DSEListGeneric<String> watchList = new DSEListGeneric<String>();

    /** Name of the brokerage firm */
    private String name;

    /**
     * Constructs a StockBroker with the specified name.
     *
     * @param name the broker's name
     */
    public StockBroker(String name) {
        this.name = name;
    }

    /**
     * @return the name of the broker
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a deep copy of the broker's watchlist
     *
     * @return deep copied watchlist
     */
    public DSEListGeneric<String> getWatchlist() {
        return new DSEListGeneric<String>(watchList);
    }

    /**
     * Adds a company code to the broker's watchlist if not already present and not null
     *
     * @param companyCode the code to watch
     * @return true if successfully added, false otherwise
     */
    public boolean addWatchlist(String companyCode) {
        if (companyCode == null || watchList.contains(companyCode)) {
            return false;
        }
        return watchList.add(companyCode);
    }

    /**
     * Places a trade order if it's valid and not already in the pending list
     *
     * @param order the trade to place
     * @return true if successfully added
     */
    public boolean placeOrder(Trade order) {
        if (order == null || pendingTrades.contains(order)) {
            return false;
        }
        return pendingTrades.offer(order);
    }

    /**
     * Gets and removes the next trade to process from the queue
     *
     * @return the next Trade, or null if none
     */
    public Trade getNextTrade() {
        return pendingTrades.poll();
    }

    /**
     * @return the number of pending trades in the queue
     */
    public int getPendingTradeCount() {
        return pendingTrades.size();
    }

    /**
     * Equality check based on broker name (used for testing)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StockBroker other = (StockBroker) obj;
        if (name == null) {
            return other.name == null;
        } else return name.equals(other.name);
    }
}
