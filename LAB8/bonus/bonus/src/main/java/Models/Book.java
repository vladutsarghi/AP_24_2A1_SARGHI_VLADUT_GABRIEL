package Models;

import DAO.BookAuthorsDAO;
import DAO.BookDAO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.sql.DataSource;
import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
public class Book {
    int id;
    DataSource dataSource;
    private String title;
    private String genre;
    private String publicationDate;
    List<String> authors;

    BookDAO bookDAO ;
    BookAuthorsDAO bookAuthorsDAO;
    public Book(DataSource dataSource) {
        this.dataSource = dataSource;
        bookDAO = new BookDAO(dataSource);
        bookAuthorsDAO = new BookAuthorsDAO(dataSource);
    }

    public Book(int id, String title, String genre) {
        this.id = id;
        this.title = title;
        this.genre = genre;
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

    public List<String> getAuthors() {
        return authors;
    }

    public int getId(){
        return this.id;
    }

}