package cognizant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Task33 {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydata";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "usha";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Money Transfer Transaction Example ---");

        displayBalances();

        System.out.println("\nAttempting to transfer $100 from Alice (101) to Bob (102)...");
        transferMoney(101, 102, 100.00);
        displayBalances();

        System.out.println("\nAttempting to transfer $1000 from Charlie (103) to Alice (101)...");
        transferMoney(103, 101, 1000.00);
        displayBalances();

        System.out.println("\nAttempting to transfer $50 from NonExistent (999) to Bob (102)...");
        transferMoney(999, 102, 50.00);
        displayBalances();

        System.out.println("\nAttempting to transfer $50 from Alice (101) to NonExistent (999)...");
        transferMoney(101, 999, 50.00);
        displayBalances();

        scanner.close();
    }

    public static void transferMoney(int fromAccountId, int toAccountId, double amount) {
        Connection connection = null;
        PreparedStatement debitStmt = null;
        PreparedStatement creditStmt = null;
        boolean success = false;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            
            connection.setAutoCommit(false);

            String debitSql = "UPDATE accounts SET balance = balance - ? WHERE id = ? AND balance >= ?";
            debitStmt = connection.prepareStatement(debitSql);
            debitStmt.setDouble(1, amount);
            debitStmt.setInt(2, fromAccountId);
            debitStmt.setDouble(3, amount);

            int debitRows = debitStmt.executeUpdate();

            if (debitRows == 0) {
                System.out.println("Transfer failed: Insufficient funds or sender account (ID: " + fromAccountId + ") not found.");
                connection.rollback();
                return;
            }

            String creditSql = "UPDATE accounts SET balance = balance + ? WHERE id = ?";
            creditStmt = connection.prepareStatement(creditSql);
            creditStmt.setDouble(1, amount);
            creditStmt.setInt(2, toAccountId);

            int creditRows = creditStmt.executeUpdate();

            if (creditRows == 0) {
                System.out.println("Transfer failed: Receiver account (ID: " + toAccountId + ") not found.");
                connection.rollback();
                return;
            }

            connection.commit();
            success = true;
            System.out.println("Transfer of $" + amount + " from " + fromAccountId + " to " + toAccountId + " successful!");

        } catch (SQLException e) {
            System.err.println("Database error during transfer: " + e.getMessage());
            e.printStackTrace();
            try {
                if (connection != null) {
                    System.out.println("Transaction rolled back due to error.");
                    connection.rollback();
                }
            } catch (SQLException rollbackEx) {
                System.err.println("Error during rollback: " + rollbackEx.getMessage());
            }
        } finally {
            try {
                if (debitStmt != null) debitStmt.close();
                if (creditStmt != null) creditStmt.close();
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException closeEx) {
                System.err.println("Error closing resources: " + closeEx.getMessage());
            }
        }
    }

    public static void displayBalances() {
        String selectSql = "SELECT id, account_holder_name, balance FROM accounts ORDER BY id";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSql)) {

            System.out.println("\n--- Current Account Balances ---");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") +
                                   ", Name: " + resultSet.getString("account_holder_name") +
                                   ", Balance: $" + String.format("%.2f", resultSet.getDouble("balance")));
            }
            System.out.println("--------------------------------");

        } catch (SQLException e) {
            System.err.println("Error displaying balances: " + e.getMessage());
            e.printStackTrace();
        }
    }
}