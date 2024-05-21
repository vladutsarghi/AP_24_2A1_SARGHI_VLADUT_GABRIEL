package Repositories;

import Entities.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.DatabaseUtils;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookRepository extends DataRepository<Book, Integer>{

    private final EntityManager entityManager;
    private static final Logger logger = Logger.getLogger(BookRepository.class.getName());
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
        try{
            return entityManager.find(Book.class, id);
        }catch(Exception e){
            logger.log(Level.SEVERE, "Exception in Book.findById()", e);
            throw e;
        }

    }

    public List<Book> findByName(String name){
        try{
            TypedQuery<Book> query = entityManager.createNamedQuery("Book.findByName", Book.class);
            query.setParameter("name", name);
            return query.getResultList();
        }catch(Exception e){
            logger.log(Level.SEVERE, "Exception in Book.findByName()", e);
            throw e;
        }
    }

    public List<Book> findByAuthorId(Integer id){
        try{
            TypedQuery<Book> query = entityManager.createNamedQuery("Book.findByAuthorId", Book.class);
            query.setParameter("authorId",  id);
            return query.getResultList();
        }catch (Exception e){
            logger.log(Level.SEVERE, "Exception in Book.findByAuthorId()", e);
            throw e;
        }

    }


}
