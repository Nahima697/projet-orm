package com.hb.cda.projetorm.repository;

import com.hb.cda.projetorm.entity.JobApplication;
import com.hb.cda.projetorm.entity.Project;
import com.hb.cda.projetorm.entity.ProjectOwner;
import com.hb.cda.projetorm.repository.util.AbstractGenericRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.criteria.*;

import java.util.List;

public class JobApplicationRepository extends AbstractGenericRepository<JobApplication,Integer> {
    public JobApplicationRepository(EntityManagerFactory emf, Class<JobApplication> entityClass) {
        super(emf, entityClass);
    }

    public List<JobApplication> findByProjectName(String projectName) {
        try (EntityManager em = emf.createEntityManager()) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<JobApplication> query = cb.createQuery(JobApplication.class);
            Root<JobApplication> root = query.from(JobApplication.class);
            Join<JobApplication, Project> projectJoin = root.join("project");

            query.select(root)
                    .where(cb.equal(projectJoin.get("projectName"), projectName));

            return em.createQuery(query).getResultList();
        } catch (PersistenceException e) {
            System.err.println(e);
        }
        return List.of();
    }
}
