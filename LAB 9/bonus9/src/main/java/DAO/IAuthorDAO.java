package DAO;

import Entities.Author;



import java.util.List;

public interface IAuthorDAO {
    public void create (Author book);
    public Author findById(int id);
    public List<Author> findByName(String name);
}
