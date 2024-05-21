package DAO;

import Repositories.AuthorDAO;
import Repositories.BookDAO;

import java.sql.Connection;

public class JDBCFactory extends DAOFactory {
    private final Connection connection;

    public JDBCFactory(Connection connection) {
        this.connection = connection;
    }

    @Override
    public IBookDAO getBookDAO() {
        return new BookDAO(connection);
    }

    @Override
    public IAuthorDAO getAuthorDAO() {
        return new AuthorDAO(connection);
    }

    @Override
    public IPublishingHouseDAO getPublishingHouseDAO() {
        return null;
    }

}
