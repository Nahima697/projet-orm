package com.hb.cda.projetorm;

import com.hb.cda.projetorm.database.JpaUtil;
import com.hb.cda.projetorm.entity.*;
import com.hb.cda.projetorm.repository.*;
import com.hb.cda.projetorm.repository.interfaces.DevelopperRepository;
import com.hb.cda.projetorm.repository.interfaces.JobApplicationRepository;
import com.hb.cda.projetorm.repository.interfaces.ProjectOwnerRepository;
import com.hb.cda.projetorm.repository.interfaces.ProjectRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            // Création des thèmes
            Theme javaTheme = new Theme();
            javaTheme.setName("Java");
            javaTheme.setDescription("Technologie Java");

            Theme springTheme = new Theme();
            springTheme.setName("Spring");
            springTheme.setDescription("Framework Spring");
            springTheme.setParent(javaTheme);

            Theme backendTheme = new Theme();
            backendTheme.setName("Backend");
            backendTheme.setDescription("Développement côté serveur");

            em.persist(javaTheme);
            em.persist(springTheme);
            em.persist(backendTheme);

            // Création des Users
            User user1 = new User("john@john.fr", "$2y$10$1shck7XRwOh3QhoRtbiJWuLZq0EcOPUQow8MzeHQxT9GDNMG2fpxW");
            User user2 = new User("jill@example.com", "$2y$10$1shck7XRwOh3QhoRtbiJWuLZq0EcOPUQow8MzeHQxT9GDNMG2fpxW");
            em.persist(user1);
            em.persist(user2);

            // Project Owner
            ProjectOwner owner = new ProjectOwner();
            owner.setUsername("John Doe");
            owner.setUser(user1);
            em.persist(owner);

            // Developpers
            Developper dev = new Developper();
            dev.setUsername("John Doe developper");
            dev.setUser(user1);
            em.persist(dev);

            Developper dev2 = new Developper();
            dev2.setUsername("Jill Doe developper");
            dev2.setUser(user2);
            em.persist(dev2);

            // Projects
            Project project1 = new Project();
            project1.setProjectName("Gestion de projets");
            project1.setProjectDescription("Application de gestion de projets interne");
            project1.setProjectOwner(owner);
            project1.setThemes(List.of(javaTheme, springTheme));
            project1.setBudget(10025.5);
            em.persist(project1);

            Project project2 = new Project();
            project2.setProjectName("Site e-commerce");
            project2.setProjectDescription("Application de site de ventes en ligne");
            project2.setProjectOwner(owner);
            project2.setThemes(List.of(backendTheme));
            em.persist(project2);

            // JobApplication
            List<JobApplication> jobs = new ArrayList<>();
            JobApplication jobApplication = new JobApplication();
            jobApplication.setProject(project1);
            jobs.add(jobApplication);
            dev.setJobApplications(jobs);
            em.getTransaction().commit();

            // Repos
            DevelopperRepository developperRepository = new DevelopperRepositoryImpl(emf, Developper.class);
            for (Developper d : developperRepository.findAll()) {
                System.out.println(d);
            }

            JobApplicationRepository jobApplicationRepository = new JobApplicationRepositoryImpl(emf, JobApplication.class);
            System.out.println(jobApplicationRepository.findByProjectName("Site e-commerce"));

            ProjectOwnerRepository projectOwnerRepository = new ProjectOwnerRepositoryImpl(emf, ProjectOwner.class);
            for (ProjectOwner po : projectOwnerRepository.findAll()) {
                System.out.println(po);
            }

            ProjectRepository projectRepository = new ProjectRepositoryImpl(emf, Project.class);
            for (Project p : projectRepository.findByBudget(project1.getBudget())) {
                System.out.println(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
