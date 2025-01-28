# Single Responsibility Principle (SRP)

The **Single Responsibility Principle (SRP)** is one of the five principles in SOLID, a set of object-oriented design principles that promote cleaner, more maintainable code. SRP states that:

> **"A class should have only one reason to change, meaning that it should have only one responsibility."**

## Key Points of SRP:
1. **One Responsibility**: A class should focus on one task or responsibility. It should not be concerned with multiple unrelated functions. If a class has more than one responsibility, it will be harder to maintain, test, and extend.

2. **Avoiding Overloaded Classes**: When a class takes on multiple responsibilities, it becomes harder to understand and manage. Changes to one responsibility could inadvertently affect others, making the code more fragile and harder to maintain.

3. **Increased Cohesion**: By keeping a class focused on a single responsibility, its internal logic becomes more cohesive and easier to follow. This makes the class easier to modify and test.

## Example of Violating SRP:

In this example, a class has multiple responsibilities:

```java
public class Marker {
    private double amount;
    private int quantity;

    public Invoice(double amount, int quantity) {
        this.amount = amount;
        this.quantity = quantity;
    }

    // Responsibility 1: Calculating the total
    public double calculateTotal() {
        return amount * quantity; 
    }

    // Responsibility 2: Printing the invoice of the total
    public void printInvoice() {
        // Code to print the invoice
        System.out.println("Printing invoice...");
    }
    
    // Responsibility 3: Saving the invoice to the database
    public void saveToDatabase() {
        // Code to save invoice to the database
        System.out.println("Saving invoice to database...");
    }

}
```

In this example, the `Marker` class is responsible for multiple tasks:

1. Calculating the total - any change in logic for calculating total like for example adding GST to the total would result in change of this task/responsibility
2. Saving the invoice to the database - any change in database or tables would result i changing the logic for this task.
3. Printing the invoice - again any change in the way to print the invoice would need changes in this task

Each of these responsibilities is unrelated, and changing one aspect (like how to print an invoice) could inadvertently affect others (like saving to the database), making the class harder to maintain.

### Refactoring to Apply SRP:

We can refactor the `Marker` class to follow SRP by splitting it into separate classes, each with one responsibility:

```java
// Class that represents the calculation responsibility
public class Marker {
    private double amount;
    private int quantity;

    public Invoice(double amount, int quantity) {
        this.amount = amount;
        this.quantity = quantity;
    }

    // Responsibility 1: Calculating the total
    public double calculateTotal() {
        return amount * quantity;
    }
    
    //Getter methods
    public double getAmount() {
        return amount;
    }
    
    public int getQuantity() {
        return quantity;
    }

}

// Class responsible for printing the invoice
public class InvoicePrinter {
    public void printInvoice(Marker marker) {
        System.out.println("Printing invoice for " + marker.getQuantity() + "marker" + (marker.getQuantity() > 1 ? "s" : ""));
        System.out.println("Total: " + marker.calculateTotal());
    }
}

// Class responsible for saving the invoice to the database
public class InvoiceRepository {
    public void saveToDatabase(Marker marker) {
        // Code to save invoice to the database
        System.out.println("Saving invoice to database...");
    }
}

```
### Explanation:

- **`Marker` class**: This class now focuses only on the data (quantity and amount) and the calculation of the total. It no longer handles saving or printing.

- **`InvoicePrinter` class**: This class handles only the printing responsibility. It is now responsible for formatting and printing the invoice.

- **`InvoiceRepository` class**: This class is responsible for the database interaction. It handles the logic of saving the invoice to a database.

### Benefits of SRP:

- **Single Responsibility**: Each class has a clear, distinct responsibility.
- **Easier Maintenance**: If we need to change how invoices are printed or saved, we can do so independently in the `InvoicePrinter` or `InvoiceRepository` classes without affecting the core data or calculation logic.
- **Better Testability**: Each class can be tested independently.
- **Reduced Coupling**: The `Marker` class is now decoupled from database and printing logic.
