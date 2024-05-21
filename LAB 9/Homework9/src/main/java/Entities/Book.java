package Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.concurrent.Flow;

@Getter
@Setter
@Entity
@Table(name = "books", schema = "library")
@NamedQueries({
        @NamedQuery(name = "Book.findByName", query = "Select b from Book b where b.title like :name"),
        @NamedQuery(name = "Book.findByAuthorId", query = "select b from Book b Join BookAuthor ba ON b.id = ba.book.id where ba.author.id = :authorId ")
})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "genre")
    private String genre;

    @Column(name = "publication_date")
    private String publicationDate;



    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                '}';
    }
}