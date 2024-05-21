package Repositories;

import DAO.IBookDAO;
import Entities.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.DatabaseUtils;

import java.util.List;


public class BookRepository implements IBookDAO {

    private final EntityManager entityManager;

    public BookRepository() {
        entityManager = DatabaseUtils.getInstance().getEntityManager();
    }

    protected Class<Book> getEntityClass() {
        return Book.class;
    }

    public void create(Book book) {
        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.getTransaction().commit();
    }

    public Book findById(int id) {
            return entityManager.find(Book.class, id);
    }

    public List<Book> findByName(String name){

            TypedQuery<Book> query = entityManager.createNamedQuery("Book.findByName", Book.class);
            query.setParameter("name", name);
            return query.getResultList();

    }

    public List<Book> findByAuthorId(Integer id){

            TypedQuery<Book> query = entityManager.createNamedQuery("Book.findByAuthorId", Book.class);
            query.setParameter("authorId",  id);
            return query.getResultList();

    }

    public List<Book> findAll(){

        TypedQuery<Book> query = entityManager.createNamedQuery("Book.findAll", Book.class);
        return query.getResultList();

    }

    public List findBooksByFirstLetter(char firstLetter) {
        String firstLetterStr = String.valueOf(firstLetter).toUpperCase();
        return entityManager.createNativeQuery("SELECT * FROM books WHERE UPPER(SUBSTRING(title, 1, 1)) = :firstLetter", Book.class)
                .setParameter("firstLetter", firstLetterStr)
                .getResultList();
    }
}
