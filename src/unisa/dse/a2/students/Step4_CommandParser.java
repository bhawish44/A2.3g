package unisa.dse.a2.students;

import java.util.Scanner;

/**
 * Parses and executes command-line instructions on a SecuritiesExchange instance.
 */
public class Step4_CommandParser {

    public int runCommandLineExchange(SecuritiesExchange exchange, Scanner sc) {
        int tradesProcessed = 0;
        boolean keepRunning = true;

        while (sc.hasNextLine() && keepRunning) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split(" ");
            String command = parts[0].toUpperCase();

            switch (command) {
                case "LISTED": {
                    // LISTED code name price
                    if (parts.length < 4) break;
                    String code = parts[1];
                    String name = parts[2];
                    int price = Integer.parseInt(parts[3]);
                    exchange.addCompany(new ListedCompany(code, name, price));
                    break;
                }
                case "BROKER": {
                    // BROKER name
                    if (parts.length < 2) break;
                    exchange.addBroker(new StockBroker(parts[1]));
                    break;
                }
                case "WATCH": {
                    // WATCH broker companycode
                    if (parts.length < 3) break;
                    String brokerName = parts[1];
                    String companyCode = parts[2];
                    for (int i = 0; i < exchange.brokers.size(); i++) {
                        StockBroker b = exchange.brokers.get(i);
                        if (b.getName().equals(brokerName)) {
                            b.addWatchlist(companyCode);
                            break;
                        }
                    }
                    break;
                }
                case "TRADE": {
                    // TRADE broker companycode quantity
                    if (parts.length < 4) break;
                    String brokerName = parts[1];
                    String companyCode = parts[2];
                    int qty = Integer.parseInt(parts[3]);
                    for (int i = 0; i < exchange.brokers.size(); i++) {
                        StockBroker b = exchange.brokers.get(i);
                        if (b.getName().equals(brokerName)) {
                            boolean placed = b.placeOrder(new Trade(b, companyCode, qty));
                            System.out.println("Placed trade for " + companyCode + "? " + placed);
                            System.out.println("Pending trade count: " + b.getPendingTradeCount());
                            break;
                        }
                    }
                    break;
                }
                case "PROCESS": {
                    try {
                        tradesProcessed += exchange.processTradeRound();
                    } catch (UntradedCompanyException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                }
                case "EXIT": {
                    keepRunning = false;
                    break;
                }
                default:
                    System.err.println("Unknown command: " + command);
                    break;
            }
        }

        return tradesProcessed;
    }
}
