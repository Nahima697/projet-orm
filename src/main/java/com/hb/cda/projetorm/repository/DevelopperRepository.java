package com.hb.cda.projetorm.repository;
import com.hb.cda.projetorm.entity.Developper;
import java.util.List;

import com.hb.cda.projetorm.entity.Mastery;
import com.hb.cda.projetorm.entity.Techno;
import com.hb.cda.projetorm.repository.util.AbstractGenericRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;

public class DevelopperRepository extends AbstractGenericRepository<Developper,Integer> {

    public DevelopperRepository(EntityManagerFactory emf, Class<Developper> entityClass) {
        super(emf, entityClass);
    }

    @Override
    public List<Developper> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("SELECT d FROM Developper d LEFT JOIN FETCH d.devMasteryTechnos", Developper.class)
                    .getResultList();
        } catch (PersistenceException e) {
            System.err.println(e);
        }
        return null;
    }

    public List<Developper> findByTechno(Techno techno) {
        try (EntityManager em = emf.createEntityManager()) {
        return em.createQuery("SELECT d FROM Developper  d LEFT JOIN FETCH d.devMasteryTechnos dmt WHERE dmt.techno = :techno")
                .setParameter("techno",techno)
                .getResultList();
        }
        catch(PersistenceException e) {
            System.err.println(e);
        }
        return List.of();
    }

    public List<Developper> findByMasteryTechno(Techno techno, Mastery mastery) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("SELECT d FROM Developper  d LEFT JOIN FETCH d.devMasteryTechnos dmt WHERE dmt.techno = :techno AND dmt.mastery = :mastery")
                    .setParameter("techno",techno)
                    .setParameter("mastery",mastery)
                    .getResultList();
        }
        catch(PersistenceException e) {
            System.err.println(e);
        }
        return List.of();
    }
}