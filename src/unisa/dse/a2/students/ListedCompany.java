package unisa.dse.a2.students;

/**
 * Represents a company listed on the exchange that can be traded.
 */
public class ListedCompany {

    /** The full name of the company */
    private String name;

    /** The listing code of the company */
    private String code;

    /** Current price of the company after the last trade */
    private int currentPrice;

    /**
     * Constructor to initialize the listed company with its code, name, and starting price.
     *
     * @param code          the stock code of the company
     * @param name          the full name of the company
     * @param currentPrice  the current trading price
     */
    public ListedCompany(String code, String name, int currentPrice) {
        this.code = code;
        this.name = name;
        this.currentPrice = currentPrice;
    }

    /**
     * @return the full name of the company
     */
    public String getName() {
        return name;
    }

    /**
     * @return the listing code of the company
     */
    public String getCode() {
        return code;
    }

    /**
     * @return the current trading price of the company
     */
    public int getCurrentPrice() {
        return currentPrice;
    }

    /**
     * Adjusts the company's share price based on trade quantity.
     * Increases price if quantity > 0 (buy), decreases if < 0 (sell).
     * Price is increased/decreased by quantity / 100, minimum price is 1.
     *
     * @param quantity the number of shares traded
     * @return the updated price after processing
     */
    public int processTrade(int quantity) {
        currentPrice += quantity / 100;
        if (currentPrice < 1) {
            currentPrice = 1;
        }
        return currentPrice;
    }
}
