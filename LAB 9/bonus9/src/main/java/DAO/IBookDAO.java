package DAO;

import Entities.Book;

import java.util.List;

public interface IBookDAO {
    public void create (Book book);
    public Book findById(int id);
    public List<Book> findByName(String name);
    public List<Book> findByAuthorId(Integer id);
    public List<Book> findAll();
}
