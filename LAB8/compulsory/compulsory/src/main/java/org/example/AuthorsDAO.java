package org.example;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorsDAO {
    private final DatabaseConnectionManager dbManager;

    public AuthorsDAO() {
        this.dbManager = DatabaseConnectionManager.getInstance();
    }

    public void addAuthor(String name){
        try{
            PreparedStatement statement = dbManager.getConnection().prepareStatement(
                    "INSERT INTO authors (name) VALUES (?)");

            statement.setString(1, name);

            statement.executeUpdate();

            System.out.println("Book inserted successfully.");
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAuthor(String name){
        try{
            PreparedStatement statement = dbManager.getConnection().prepareStatement(
                    "DELETE FROM authors WHERE name = ?");

            statement.setString(1, name);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Author deleted successfully.");
            } else {
                System.out.println("No Author found with the specified name.");
            }

            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
