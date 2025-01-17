import java.util.ArrayList;
import java.util.Scanner;

class Expense {
    private int id;
    private String category;
    private double amount;
    private String date;
    private String description;

    public Expense(int id, String category, double amount, String date, String description) {
        this.id = id;
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Category: " + category + ", Amount: $" + amount + ", Date: " + date + ", Description: " + description;
    }
}

class ExpenseTracker {
    private ArrayList<Expense> expenses;
    private double monthlyBudget;

    public ExpenseTracker() {
        this.expenses = new ArrayList<>();
        this.monthlyBudget = 0.0;
    }

    public void addExpense(int id, String category, double amount, String date, String description) {
        Expense expense = new Expense(id, category, amount, date, description);
        expenses.add(expense);
        System.out.println("Expense added successfully!");
    }

    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded yet.");
        } else {
            System.out.println("--- All Expenses ---");
            for (Expense expense : expenses) {
                System.out.println(expense);
            }
        }
    }

    public void filterExpensesByCategory(String category) {
        System.out.println("--- Expenses for Category: " + category + " ---");
        for (Expense expense : expenses) {
            if (expense.getCategory().equalsIgnoreCase(category)) {
                System.out.println(expense);
            }
        }
    }

    public void filterExpensesByDate(String date) {
        System.out.println("--- Expenses for Date: " + date + " ---");
        for (Expense expense : expenses) {
            if (expense.getDate().equals(date)) {
                System.out.println(expense);
            }
        }
    }

    public void setMonthlyBudget(double budget) {
        this.monthlyBudget = budget;
        System.out.println("Monthly budget set to $" + budget);
    }

    public void viewExpenseSummary() {
        double total = 0.0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }

        System.out.println("--- Expense Summary ---");
        System.out.println("Total Expenses: $" + total);

        if (monthlyBudget > 0) {
            if (total > monthlyBudget) {
                System.out.println("Budget Status: Exceeded by $" + (total - monthlyBudget));
            } else {
                System.out.println("Budget Status: Within Budget. Remaining: $" + (monthlyBudget - total));
            }
        } else {
            System.out.println("No budget set.");
        }
    }
}

public class ExpenseTrackerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseTracker tracker = new ExpenseTracker();
        int idCounter = 1;

        while (true) {
            System.out.println("\n--- Expense Tracker Menu ---");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. Filter Expenses by Date");
            System.out.println("4. Filter Expenses by Category");
            System.out.println("5. Set Monthly Budget");
            System.out.println("6. View Expense Summary");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter Amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Date (yyyy-MM-dd): ");
                    String date = scanner.nextLine();
                    System.out.print("Enter Description: ");
                    String description = scanner.nextLine();
                    tracker.addExpense(idCounter++, category, amount, date, description);
                    break;
                case 2:
                    tracker.viewExpenses();
                    break;
                case 3:
                    System.out.print("Enter Date (yyyy-MM-dd): ");
                    String filterDate = scanner.nextLine();
                    tracker.filterExpensesByDate(filterDate);
                    break;
                case 4:
                    System.out.print("Enter Category: ");
                    String filterCategory = scanner.nextLine();
                    tracker.filterExpensesByCategory(filterCategory);
                    break;
                case 5:
                    System.out.print("Enter Monthly Budget: ");
                    double budget = scanner.nextDouble();
                    tracker.setMonthlyBudget(budget);
                    break;
                case 6:
                    tracker.viewExpenseSummary();
                    break;
                case 7:
                    System.out.println("Exiting Expense Tracker. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
