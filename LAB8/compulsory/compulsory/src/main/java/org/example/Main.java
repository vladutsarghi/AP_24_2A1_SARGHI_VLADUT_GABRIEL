package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // JDBC URL, username, and password
        AuthorsDAO authorsDAO = new AuthorsDAO();

        authorsDAO.addAuthor("Vladut1");
        authorsDAO.addAuthor("Vladut2");
        authorsDAO.addAuthor("Vladut3");

       // authorsDAO.deleteAuthor("Vladut");

        DatabaseConnectionManager db = DatabaseConnectionManager.getInstance();
        db.closeConnection();
    }
}
