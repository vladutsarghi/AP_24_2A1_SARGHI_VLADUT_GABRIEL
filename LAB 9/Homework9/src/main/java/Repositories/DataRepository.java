package Repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.Getter;
import org.example.DatabaseUtils;

import java.io.Serializable;
import java.util.function.Consumer;

public abstract class DataRepository<T, ID extends Serializable> {
    protected abstract Class<T> getEntityClass();

    // CRUD Operations
    private boolean runWithRollback(T entity, Consumer<T> consumer) {
        try {
            beginTransaction();
            consumer.accept(entity);
            commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            rollback();
            return false;
        }
    }
    public T findById(ID id) {
        return getEntityManager().find(getEntityClass(), id);
    }

    public boolean delete(T entity) {
        return runWithRollback(entity, (e) -> getEntityManager().remove(e));
    }

    public boolean save(T entity) {
        return runWithRollback(entity, (e) -> getEntityManager().persist(e));
    }


    // // EntityManager Specific
    @Getter
    private final EntityManager entityManager = DatabaseUtils.getInstance().getEntityManager();
    private EntityTransaction entityTransaction;

    private void beginTransaction() {
        entityTransaction = getEntityManager().getTransaction();
        entityTransaction.begin();
    }

    private void commit() {
        entityTransaction.commit();
    }

    private void rollback() {
        entityTransaction.rollback();
    }

}
