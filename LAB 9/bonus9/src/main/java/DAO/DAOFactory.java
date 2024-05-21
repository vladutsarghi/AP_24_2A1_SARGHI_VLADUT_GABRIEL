package DAO;

public abstract class DAOFactory {
    public abstract IBookDAO getBookDAO();

    public abstract IAuthorDAO getAuthorDAO();

    public abstract IPublishingHouseDAO getPublishingHouseDAO();

    public static DAOFactory getDAOFactory(String type, Object connection) {
        if ("JPA".equalsIgnoreCase(type)) {
            return new JPAFactory((jakarta.persistence.EntityManager) connection);
        } else if ("JDBC".equalsIgnoreCase(type)) {
            return new JDBCFactory((java.sql.Connection) connection);
        }
        return null;
    }
}
