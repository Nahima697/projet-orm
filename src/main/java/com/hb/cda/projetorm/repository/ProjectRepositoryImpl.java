package com.hb.cda.projetorm.repository;

import com.hb.cda.projetorm.entity.Project;
import com.hb.cda.projetorm.entity.Status;
import com.hb.cda.projetorm.entity.Theme;
import com.hb.cda.projetorm.repository.interfaces.ProjectRepository;
import com.hb.cda.projetorm.repository.util.AbstractGenericRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;

import java.time.LocalDate;
import java.util.List;


public class ProjectRepositoryImpl extends AbstractGenericRepository<Project,Integer>
implements ProjectRepository {

    public ProjectRepositoryImpl(EntityManagerFactory emf, Class<Project> entityClass) {
        super(emf, entityClass);
    }

    @Override
    public List<Project> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("SELECT p FROM Project p LEFT JOIN FETCH p.jobApplications",
                            Project.class)
                    .getResultList();
        } catch (PersistenceException e) {
            System.err.println(e);
        }
        return List.of();
    }

    public List<Project> findByEndDate(LocalDate enDate) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("SELECT p FROM Project p WHERE p.endDate = :endDate",
                            Project.class)
                    .setParameter("endDate", enDate)
                    .getResultList();
        } catch (PersistenceException e) {
            System.err.println(e);
        }
        return List.of();
    }

    public List<Project> findByBudget(Double budget) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("SELECT p FROM Project p WHERE p.budget <= :budget",
                            Project.class)
                    .setParameter("budget", budget)
                    .getResultList();
        } catch (PersistenceException e) {
            System.err.println(e);
        }
        return List.of();
    }

    public List<Project> findByStatus(Status status) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("SELECT p FROM Project p WHERE p.status = :status",
                            Project.class)
                    .setParameter("status", status)
                    .getResultList();
        } catch (PersistenceException e) {
            System.err.println(e);
        }
        return List.of();
    }

    public List<Project> findByJobApplicationStatus(Status status) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("SELECT p FROM Project p LEFT JOIN FETCH p.jobApplications ja WHERE ja.status = :status", Project.class)
                    .setParameter("status", status)
                    .getResultList();
        } catch (PersistenceException e) {
            System.err.println(e);

        }
        return List.of();
    }
    public List<Project> findByProjectTheme(Theme theme) {
        try(EntityManager em = emf.createEntityManager()) {
            return em.createQuery("SELECT FROM Project p LEFT JOIN FETCH p.theme t WHERE t = :theme",Project.class)
                    .setParameter("theme", theme)
                    .getResultList();
        }
        catch(PersistenceException e) {
            System.err.println(e);
        }
        return List.of();
    }
}