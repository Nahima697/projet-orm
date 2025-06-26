package com.hb.cda.projetorm;

import com.hb.cda.projetorm.database.JpaUtil;
import com.hb.cda.projetorm.entity.*;
import com.hb.cda.projetorm.repository.*;
import com.hb.cda.projetorm.repository.interfaces.DevelopperRepository;
import com.hb.cda.projetorm.repository.interfaces.ProjectRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;
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

            //Techno
            Techno javaTechno = new Techno();
            javaTechno.setName("Java");
            em.persist(javaTechno);

            //DevMasteryTechno
            DevMasteryTechno devMastery = new DevMasteryTechno();
            devMastery.setDevelopper(dev);
            devMastery.setTechno(javaTechno);
            devMastery.setMastery(Mastery.SENIOR);
            em.persist(devMastery);
            dev.getDevMasteryTechnos().add(devMastery);

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

            //Initialisation des repo de type interface
            DevelopperRepository devrepo = new DevelopperRepositoryImpl(emf, Developper.class);
            ProjectRepository projectRepository = new ProjectRepositoryImpl(emf, Project.class);

            // ==========================================================
            // TESTS DE DEVELOPPEUR REPOSITORY
            // ==========================================================
            List<Developper> allDevelopers = devrepo.findAll();
            System.out.println("Tous les développeurs : " + allDevelopers);

            List<Developper> javaDevs = devrepo.findByTechno(javaTechno);
            System.out.println("Développeurs maîtrisant Java : " + javaDevs);

            List<Developper> javaExpertDevs = devrepo.findByMasteryTechno(javaTechno, Mastery.SENIOR);
            System.out.println("Développeurs maîtrisant Java niveau 'Expert' : " + javaExpertDevs);

            // ==========================================================
            // TESTS DE PROJECT REPOSITORY
            // ==========================================================
            List<Project> projectsByEndDate = projectRepository.findByEndDate(LocalDate.now().plusDays(30));
            System.out.println("Projets finissant à la date donnée : " + projectsByEndDate);

            List<Project> projectsByBudget = projectRepository.findByBudget(15000.0);
            System.out.println("Projets ayant un budget <= 15000.0 : " + projectsByBudget);

            List<Project> projectsByStatus = projectRepository.findByStatus(Status.PENDING);
            System.out.println("Projets ayant le status EN_COURS : " + projectsByStatus);

            List<Project> projectsByJobAppStatus = projectRepository.findByJobApplicationStatus(Status.PENDING);
            System.out.println("Projets ayant des JobApplications EN_COURS : " + projectsByJobAppStatus);

            List<Project> projectsWithTheme = projectRepository.findByProjectTheme(springTheme);
            System.out.println("Projets ayant pour thème 'Spring' : " + projectsWithTheme);
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
