package DAO;

import Models.Book;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private final DataSource dataSource;

    public BookDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addBook(Book book){
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO books (title, genre, publication_date) VALUES (?, ?, ?)");

            statement.setString(1, book.getName());
            statement.setString(2, book.getGenre());
            statement.setString(3,book.getPublicationDate());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Carte inserată cu succes.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM books");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String genre = resultSet.getString("genre");
                Book book = new Book(id, title, genre);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }
}
