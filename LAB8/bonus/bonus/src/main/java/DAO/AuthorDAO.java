package DAO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorDAO {
    private final DataSource dataSource;

    public AuthorDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addAuthor(String name){
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO authors (name) VALUES (?)");

            statement.setString(1, name);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Carte inserată cu succes.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAuthor(String name){
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM authors WHERE name = (?)");

            statement.setString(1, name);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println(rowsInserted + " sterse cu succes");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
