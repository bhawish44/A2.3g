package unisa.dse.a2.junit;

import unisa.dse.a2.students.SecuritiesExchange;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A test runner to simulate running commands from a file through SecuritiesExchange.
 */
public class TestRunnerMain {
    public static void main(String[] args) {
        try {
            // Initialize the exchange with a name
            SecuritiesExchange exchange = new SecuritiesExchange("A2X Exchange");

            // Load commands from file
            File commandFile = new File("TestCommands.txt");
            Scanner sc = new Scanner(commandFile);

            // Run the command-line exchange
            int trades = exchange.runCommandLineExchange(sc);

            // Output result summary
            System.out.println("Total trades processed: " + trades);
            System.out.println("--- Announcements ---");
            System.out.println(exchange.announcements.toString());

            sc.close();

        } catch (FileNotFoundException e) {
            System.err.println("TestCommands.txt file not found. Please make sure it is in the project root.");
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
