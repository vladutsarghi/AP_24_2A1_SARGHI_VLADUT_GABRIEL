package Models;

import DAO.AuthorDAO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.sql.DataSource;

@Getter
@Setter
@RequiredArgsConstructor
public class Author {
    DataSource dataSource;
    private String name;

    private final AuthorDAO authorDAO;

    public Author(DataSource dataSource) {
        this.dataSource = dataSource;
        authorDAO = new AuthorDAO(dataSource);
    }

    public void add(String name){
        authorDAO.addAuthor(name);
    }

    public void delete(String name){
        authorDAO.deleteAuthor(name);
    }
}