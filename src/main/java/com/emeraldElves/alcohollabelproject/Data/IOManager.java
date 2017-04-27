package com.emeraldElves.alcohollabelproject.Data;

import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Essam on 4/26/2017.
 */
final public class IOManager {
    private EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("COLA");

    private static class InstanceHolder {
        static final IOManager INSTANCE = new IOManager();
    }
    private interface Promise {
        abstract Object onCreate(Session session) throws HibernateException;
    }
    public static IOManager getInstance(){
        return InstanceHolder.INSTANCE;
    }
    private IOManager(){

    }
    @SuppressWarnings("deprecation")
    static private Object execute(Promise cb) {
        EntityManager manager = getInstance().ENTITY_MANAGER_FACTORY.createEntityManager();
        Session session = manager.unwrap(Session.class);
        session.setFlushMode(FlushMode.COMMIT);
        Transaction tx = null;
        Object ret = null;
        try {
            tx = session.beginTransaction();
            ret = cb.onCreate(session);
            tx.commit();
        }
        catch (HibernateException e){
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return ret;
    }
    static public <T extends BaseEntity> void save(T entity) {
        execute((Session session) -> {
            session.persist(entity);
            return null;
        });
        refresh(entity);
    }

    @SuppressWarnings("unchecked")
    static public <T extends BaseEntity> T retrieve(Class<T> entityClass, Serializable id) {
        return (T)execute((Session session) -> {
            return session.get(entityClass, id);
        });
    }
    @SuppressWarnings({"deprecation","unchecked"})
    static public <T extends BaseEntity> List<T> find(Class<T> entityClass, DetachedCriteria criteria) {
        return (List)execute((Session session) -> {
            if (criteria == null){
                return session.createCriteria(entityClass).list();
            }
            return criteria.getExecutableCriteria(session).list();
        });
    }
    static public <T extends BaseEntity> List<T> list(Class<T> entityClass) {
        return find(entityClass, DetachedCriteria.forClass(entityClass));
    }
    static public <T extends BaseEntity> void remove(T entity) {
        execute((Session session) -> {
            session.remove(entity);
            return null;
        });
    }
    static public <T extends BaseEntity> void refresh(T entity){
        execute((Session session) -> {
            //session.refresh(entity);
            return null;
        });
    }
}
