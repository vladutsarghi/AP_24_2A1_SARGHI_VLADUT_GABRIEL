package DAO;

import Repositories.AuthorRepository;
import Repositories.BookRepository;
import jakarta.persistence.EntityManager;

public class JPAFactory extends DAOFactory{
    private EntityManager entityManager;

    public JPAFactory(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public IBookDAO getBookDAO() {
        return new BookRepository();
    }

    @Override
    public IAuthorDAO getAuthorDAO() {
        return new AuthorRepository();
    }

    @Override
    public IPublishingHouseDAO getPublishingHouseDAO() {
        return null;
    }
}
