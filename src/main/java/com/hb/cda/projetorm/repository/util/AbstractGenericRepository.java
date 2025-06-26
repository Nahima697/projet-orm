package com.hb.cda.projetorm.repository.util;

import com.hb.cda.projetorm.repository.interfaces.GenericRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;

import java.util.List;
import java.util.Optional;

public abstract class AbstractGenericRepository <T, K> implements GenericRepository<T, K> {

    protected EntityManagerFactory emf ;
    protected Class<T> entityClass;
    protected AbstractGenericRepository(EntityManagerFactory emf, Class<T> entityClass) {
        this.emf = emf;
        this.entityClass = entityClass;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public List<T> findAll() {
        try (EntityManager em = this.getEntityManager()) {
            return em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass)
                    .getResultList();
        } catch (PersistenceException e) {
            System.err.println(e);
            return List.of();
        }
    }
    public Optional<T> findById(K id) {
        try (EntityManager em = this.getEntityManager()) {
            T entity = em.find(entityClass, id);
            return Optional.ofNullable(entity);
        } catch (PersistenceException e) {
            System.err.println(e);
        }
        return Optional.empty();
    }

    public boolean persist(T entity) {
        try(EntityManager em = this.getEntityManager()) {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            System.err.println(e);

        }
        return false;
    }

    public boolean delete(T entity) {
        try(EntityManager em = this.getEntityManager()) {
            em.getTransaction().begin();
            T merged = em.merge(entity);
            em.remove(merged);

            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            System.err.println(e);

        }
        return false;
    }


    public boolean update(T entity) {
        try(EntityManager em = this.getEntityManager())  {
            em.getTransaction().begin();
            em.merge(entity);

            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            System.err.println(e);
        }
        return false;
    }

}



