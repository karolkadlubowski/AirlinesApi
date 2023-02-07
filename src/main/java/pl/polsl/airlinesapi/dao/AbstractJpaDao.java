package pl.polsl.airlinesapi.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractJpaDao<T extends Serializable> {
    protected Class<T> clazz;

    protected EntityManager entityManager;

    public final void setClazz(final Class<T> clazzToSet) {
        this.clazz = clazzToSet;
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("airlines");
        entityManager = managerFactory.createEntityManager();
    }

    public T findOne(final int id) {
        return entityManager.find(clazz, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return entityManager.createQuery("from " + clazz.getName()).getResultList();
    }

    public T create(final T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    public T update(final T entity) {
        entityManager.getTransaction().begin();
        var updatedEntity = entityManager.merge(entity);
        entityManager.getTransaction().commit();
        return updatedEntity;
    }

    public void delete(final T entity) {
        entityManager.remove(entity);
    }

    public void deleteById(final int entityId) {
        final T entity = findOne(entityId);
        if (entity != null) {
            entityManager.getTransaction().begin();
            delete(entity);
            entityManager.getTransaction().commit();
        }
    }
}
