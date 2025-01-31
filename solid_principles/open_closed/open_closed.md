## Open/Closed Principle (OCP)

The **Open/Closed Principle (OCP)** states that:

> **"Software entities (classes, modules, functions, etc.) should be open for extension, but closed for modification."**

In other words, you should be able to add new functionality to a class without changing its existing code. This helps avoid breaking existing functionality when extending a system and improves maintainability.

### Violating OCP:

Let's take the previous example from Single Responsibility where we were saving the invoice to database which violates OCP by requiring changes to its existing code when new functionality is added:

```java
public class InvoiceRepository {
    Marker marker; 
    
    public InvoiceRepository(Marker marker){
        this.marker = marker;
    }
    public void saveToDatabase(Marker marker) {
        // Code to save invoice to the database
        System.out.println("Saving invoice to database...");
    }
    public void saveToFile(Marker marker) {
        // Code to save invoice to the file
        System.out.println("Saving invoice to file...");
    }
}
```
In this case, every time you want to save the invoice in a different way (save in a file) a new method is introduced. Thus the InvoiceRepository class needs to be modified, violating the Open/Closed Principle.

### Refactoring to Apply OCP:

To apply OCP, we can refactor the `InvoiceRepository` class by converting it to an interface for saving invoices, and then create different implementations of this interface for saving to various storage mediums (database, file, etc.). 

```java
// Step 1: Define InvoiceRepository interface
public interface InvoiceRepository {
    void save(Marker marker);
}

// Step 2: Implement concrete classes for different storage mediums
public class SaveToDB implements InvoiceRepository {
    @Override
    public void save(Marker marker) {
        // Code to save invoice to the database
        System.out.println("Saving invoice to database...");
    }
}

public class SaveToFile implements InvoiceRepository {
    @Override
    public void save(Marker marker) {
        // Code to save invoice to the file
        System.out.println("Saving invoice to file...");
    }
}



```