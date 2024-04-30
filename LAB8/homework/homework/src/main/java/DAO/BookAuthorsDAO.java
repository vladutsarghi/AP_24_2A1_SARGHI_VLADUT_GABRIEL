package DAO;

import Models.Book;

import javax.sql.DataSource;
import java.sql.*;

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
            if (e.getSQLState().startsWith("23")) { // SQL state code for integrity constraint violation
                System.out.println("This author already exists. No new record was added.");
            } else {
                throw new RuntimeException(e);
            }
        }
    }
}
