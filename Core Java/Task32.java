package cognizant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Task32 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection con = null;
        PreparedStatement pst = null;

        try {
            String url = "jdbc:mysql://localhost:3306/mydata";
            con = DriverManager.getConnection(url, "root", "usha");
            String updateQuery = "UPDATE mytab1 SET name = ? WHERE id = ?";
            pst = con.prepareStatement(updateQuery);

            System.out.print("Enter the NEW name for the student: ");
            String newName = sc.nextLine();

            System.out.print("Enter the ID of the student to update: ");
            int idToUpdate = sc.nextInt();
            pst.setString(1, newName);
            pst.setInt(2, idToUpdate); 

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(rowsAffected + " row(s) updated successfully.");
            } else {
                System.out.println("No rows updated. Student with ID " + idToUpdate + " might not exist.");
            }

        } catch (SQLException e) {
            System.err.println("Database error occurred: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
                if (sc != null) sc.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}