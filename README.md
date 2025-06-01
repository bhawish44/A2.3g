# GameStonk Share Trading Simulator

This Java project simulates a simplified stock trading platform like RobinHood.   Data structures, object-oriented design, and algorithmic decision-making are explored in this university assignment.

---

## Features Implemented

### ✅ Step 1: `DSEList.java`
- Custom doubly linked list for storing `String` data.
- Implements:
  - Blank constructor
  - Constructor from a `Node`
  - Deep copy constructor
  - Methods: `add()`, `isEmpty()`, `size()`, `toString()`, `equals()`

### ✅ Step 2: `DSEListGeneric.java`
- Generic version of the linked list
- Stores any object type (via generics)
- Implements all features of Step 1 but using `NodeGeneric<T>`

### ✅ Step 3: Core Classes Implemented
- **ListedCompany.java**
  - Company code, name, current price
  - Processes trade (ensures price >= 1)

- **Trade.java**
  - Represents a trade with quantity, company code, broker
  - Implements `Comparable` to prioritize trades

- **StockBroker.java**
  - Maintains a `PriorityQueue` of trades and a generic watchlist
  - Supports placing trades, getting pending trade count, watchlist management

- **SecuritiesExchange.java**
  - Holds brokers, companies, and announcements
  - Processes trades from each broker
  - Throws `UntradedCompanyException` if invalid company

- **UntradedCompanyException.java**
  - Custom exception showing friendly message

### ✅ Step 4 (Optional): Command Line Simulation
- Class: `Step4_CommandParser.java`
- Allows:
  - Adding listed companies and brokers
  - Setting broker watchlists
  - Placing and processing trades via commands
  - Reads commands from terminal or text file

---

## 📂 Project Structure
```
/src
|-- unisa.dse.a2.students
    |-- DSEList.java
    |-- DSEListGeneric.java
    |-- ListedCompany.java
    |-- Trade.java
    |-- StockBroker.java
    |-- SecuritiesExchange.java
    |-- Step4_CommandParser.java
    |-- UntradedCompanyException.java
|-- unisa.dse.a2.junit
    |-- [JUnit Test Classes Provided for Validation]
```

---

## 🧪 Running the Project

### Auto Marker:
- Run `AssignmentMarker.java` to see scores for each step.

### JUnit Tests:
- Run individual test files in `unisa.dse.a2.junit` to debug each class.

### Command Line Exchange:
- Create a `testcommands.txt` file:
```
LISTED AAPL Apple 100
BROKER GlobalTrade
WATCH GlobalTrade AAPL
TRADE GlobalTrade AAPL 300
PROCESS
EXIT
```
- Use `Step4_CommandParser` to simulate:
```java
Scanner sc = new Scanner(new File("testcommands.txt"));
new Step4_CommandParser().runCommandLineExchange(new SecuritiesExchange("GameStonkEX"), sc);
```

---

## 🧠 Concepts Practiced
- Linked Lists & Generic Data Structures
- Priority Queues
- Exception Handling
- Object-Oriented Design
- Command Parsing & Input Handling
- Unit Testing with JUnit


