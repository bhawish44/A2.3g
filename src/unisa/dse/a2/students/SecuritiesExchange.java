package unisa.dse.a2.students;

import java.util.HashMap;
import java.util.Scanner;
import unisa.dse.a2.interfaces.ListGeneric;

/**
 * Represents a securities exchange where brokers can place trades on listed companies.
 */
public class SecuritiesExchange {

    /** Exchange name */
    private String name;

    /** List of brokers on this exchange */
    public ListGeneric<StockBroker> brokers;

    /** List of announcements for processed trades */
    public ListGeneric<String> announcements;

    /** Map of company code to ListedCompany object */
    public HashMap<String, ListedCompany> companies;

    /**
     * Constructor initializes collections and stores exchange name
     */
    public SecuritiesExchange(String name) {
        this.name = name;
        brokers = new DSEListGeneric<>();
        announcements = new DSEListGeneric<>();
        companies = new HashMap<>();
    }

    /**
     * @return the name of the exchange
     */
    public String getName() {
        return name;
    }

    /**
     * Adds a new company to the exchange listings
     *
     * @param company the company to list
     * @return true if successfully added
     */
    public boolean addCompany(ListedCompany company) {
        if (company == null || companies.containsKey(company.getCode())) {
            return false;
        }
        companies.put(company.getCode(), company);
        return true;
    }

    /**
     * Adds a new broker to the list of exchange brokers
     *
     * @param broker the broker to register
     * @return true if successfully added
     */
    public boolean addBroker(StockBroker broker) {
        if (broker == null || brokers.contains(broker)) {
            return false;
        }
        return brokers.add(broker);
    }

    /**
     * Processes one trade from each broker who has a trade queued
     *
     * @return the number of trades successfully completed
     * @throws UntradedCompanyException if any trade references a company not on this exchange
     */
    public int processTradeRound() throws UntradedCompanyException {
        int successfulTrades = 0;

        for (int i = 0; i < brokers.size(); i++) {
            StockBroker broker = brokers.get(i);
            Trade trade = broker.getNextTrade();
            if (trade == null) continue;

            ListedCompany company = companies.get(trade.getCompanyCode());
            if (company == null) throw new UntradedCompanyException();

            int currentPrice = company.getCurrentPrice();
            company.processTrade(trade.getShareQuantity());

            String announcement = String.format("Trade: %d %s @ %d via %s",
                    trade.getShareQuantity(),
                    trade.getCompanyCode(),
                    currentPrice,
                    broker.getName());

            announcements.add(announcement);
            successfulTrades++;
        }

        return successfulTrades;
    }

    /**
     * Placeholder for command line processing logic
     */
    public int runCommandLineExchange(Scanner sc) {
        // Optional extension: implement CLI trade interface here
        return 0;
    }
}
