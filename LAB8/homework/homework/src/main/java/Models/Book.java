package Models;

import DAO.BookAuthorsDAO;
import DAO.BookDAO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.sql.DataSource;
import java.time.LocalDate;

@Setter
@Getter
@RequiredArgsConstructor
public class Book {
    DataSource dataSource;
    private String title;
    private String genre;
    //private List<Author> authorsList;
    private String publicationDate;

    BookDAO bookDAO ;
    BookAuthorsDAO bookAuthorsDAO;
    public Book(DataSource dataSource) {
        this.dataSource = dataSource;
        bookDAO = new BookDAO(dataSource);
        bookAuthorsDAO = new BookAuthorsDAO(dataSource);
    }

    public void add(String title, String genre, String publicationDate, String... authors){
        this.title = title;
        this.genre = genre;
        this.publicationDate = publicationDate;
        for(String author : authors){
            System.out.print(author);
        }
        System.out.println();
        bookDAO.addBook(this);
        bookAuthorsDAO.addBookAuthor(title, authors);
    }


    public String getName() {
        return title;
    }

    public String getGenre() {
        return genre;
    }


    public String getPublicationDate() {
        return publicationDate;
    }
}