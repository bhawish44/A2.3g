package unisa.dse.a2.students;

/**
 * Custom exception thrown when a trade involves a company not listed on the exchange.
 */
public class UntradedCompanyException extends Exception {

    /**
     * Constructs an exception with a message that includes the unlisted company code.
     *
     * @param companyCode the company code that could not be traded
     */
    public UntradedCompanyException(String companyCode) {
        super("Company code not listed: " + companyCode);
    }

    /**
     * Default constructor with generic message.
     */
    public UntradedCompanyException() {
        super("Attempted to trade a company not listed on the exchange.");
    }
}