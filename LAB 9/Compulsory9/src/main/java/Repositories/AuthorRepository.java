package Repositories;

import Entities.Author;
import Entities.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.DatabaseUtils;

import java.util.List;

public class AuthorRepository {
    private final EntityManager entityManager;

    public AuthorRepository() {
        entityManager = DatabaseUtils.getInstance().getEntityManager();
    }

    public void create(Author author) {
        entityManager.getTransaction().begin();
        entityManager.persist(author);
        entityManager.getTransaction().commit();
    }

    public Author findById(int id) {
        return entityManager.find(Author.class, id);
    }

    public List<Author> findByName(String name){
        TypedQuery<Author> query = entityManager.createNamedQuery("Author.findByName", Author.class);
        query.setParameter("name", name);
        return query.getResultList();
    }
}
