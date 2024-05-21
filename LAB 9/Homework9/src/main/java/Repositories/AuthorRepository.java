package Repositories;

import Entities.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.DatabaseUtils;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthorRepository extends DataRepository<Author, Integer>{
    private final EntityManager entityManager;
    private static final Logger logger = Logger.getLogger(AuthorRepository.class.getName());

    public AuthorRepository() {
        entityManager = DatabaseUtils.getInstance().getEntityManager();
    }

    protected Class<Author> getEntityClass() {
        return Author.class;
    }

    public void create(Author author) {
        entityManager.getTransaction().begin();
        entityManager.persist(author);
        entityManager.getTransaction().commit();
    }

    public Author findById(int id) {
        try{
            return entityManager.find(Author.class, id);
        }catch(Exception e){
            logger.log(Level.SEVERE, "Exception in Author.findById()", e);
            throw e;
        }
    }

    public List<Author> findByName(String name){
        try{
            TypedQuery<Author> query = entityManager.createNamedQuery("Author.findByName", Author.class);
            query.setParameter("name", name);
            return query.getResultList();
        }catch (Exception e){
            logger.log(Level.SEVERE, "Exception in Author.findByName()", e);
            throw e;
        }

    }
}
