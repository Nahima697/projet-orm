package com.hb.cda.projetorm.repository;

import com.hb.cda.projetorm.entity.ProjectOwner;
import com.hb.cda.projetorm.repository.interfaces.ProjectOwnerRepository;
import com.hb.cda.projetorm.repository.util.AbstractGenericRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.criteria.*;

import java.util.List;

public class ProjectOwnerRepositoryImpl extends AbstractGenericRepository<ProjectOwner,Integer>
implements ProjectOwnerRepository {

    public ProjectOwnerRepositoryImpl(EntityManagerFactory emf, Class<ProjectOwner> entityClass) {
        super(emf, entityClass);
    }

    @Override
    public List<ProjectOwner> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<ProjectOwner> query = cb.createQuery(ProjectOwner.class);
            Root<ProjectOwner> root = query.from(ProjectOwner.class);
            root.fetch("projects", JoinType.LEFT);
            query.select(root).distinct(true);
            return em.createQuery(query).getResultList();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return List.of();
    }
}
