package DAO;

import Models.Book;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookAuthorsDAO {
    private final DataSource dataSource;

    public BookAuthorsDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addBookAuthor(String title, String... authors) {
        try (Connection connection = dataSource.getConnection()) {
            for (String author : authors) {
                PreparedStatement statement2 = connection.prepareStatement(
                        "SELECT id FROM authors where name = (?)");
                statement2.setString(1, author);
                ResultSet resultSet2 = statement2.executeQuery();
                int authorId = 0;
                if (resultSet2.next()) {
                    authorId = resultSet2.getInt("id");
                }
                PreparedStatement statement1 = connection.prepareStatement(
                        "SELECT id FROM books where title = (?)");
                statement1.setString(1, title);
                ResultSet resultSet = statement1.executeQuery();
                while (resultSet.next()) {

                    int id = resultSet.getInt("id");
                    PreparedStatement statement = connection.prepareStatement(
                            "INSERT INTO book_authors (book_id, author_id) VALUES (?, ?)");

                    statement.setInt(1, id);
                    statement.setInt(2, authorId);

                    boolean rowsInserted = statement.execute();
                    if (rowsInserted == true) {
                        System.out.println("Carte inserată cu succes.");
                    }

                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getBookAuthors(int bookId){
        List<String> authors = new ArrayList<>();
        String sql = "SELECT a.name FROM authors a JOIN book_authors ba ON a.id = ba.author_id WHERE ba.book_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, bookId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                authors.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching authors", e);
        }

        return authors;
    }


    public List<String> getAuthorsByBookTitle(String bookTitle) {
        List<String> authors = new ArrayList<>();
        String sql = "SELECT a.name FROM authors a JOIN book_authors ba ON a.id = ba.author_id JOIN books b ON ba.book_id = b.id WHERE b.title = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, bookTitle);  // Set the book title in the SQL query
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                authors.add(resultSet.getString("name"));  // Add each author's name to the list
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database error occurred", e);
        }

        return authors;
    }
}
