package com.hb.cda.projetorm;

import com.hb.cda.projetorm.database.JpaUtil;
import com.hb.cda.projetorm.entity.*;
import com.hb.cda.projetorm.repository.DevelopperRepository;
import com.hb.cda.projetorm.repository.JobApplicationRepository;
import com.hb.cda.projetorm.repository.ProjectOwnerRepository;
import com.hb.cda.projetorm.repository.ProjectRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
            EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
            EntityManager em = emf.createEntityManager();

            try {
                em.getTransaction().begin();

               //  Création des thèmes
                Theme javaTheme = new Theme();
                javaTheme.setName("Java");
                javaTheme.setDescription("Technologie Java");

                Theme springTheme = new Theme();
                springTheme.setName("Spring");
                springTheme.setDescription("Framework Spring");

                Theme backendTheme = new Theme();
                backendTheme.setName("Backend");
                backendTheme.setDescription("Développement côté serveur");

                // Exemple de hiérarchie
                springTheme.setParent(javaTheme);

                em.persist(javaTheme);
                em.persist(springTheme);
                em.persist(backendTheme);

                //Création du USer

                User user1 = new User("john@john.fr","$2y$10$1shck7XRwOh3QhoRtbiJWuLZq0EcOPUQow8MzeHQxT9GDNMG2fpxW");
                em.persist(user1);

                User user2 = new User("jill@example.com","$2y$10$1shck7XRwOh3QhoRtbiJWuLZq0EcOPUQow8MzeHQxT9GDNMG2fpxW");
                em.persist(user2);


                // Création du ProjectOwner
                ProjectOwner owner = new ProjectOwner();
                owner.setUsername("John Doe");
                owner.setUser(user1);
                em.persist(owner);

                //Création d'un dev
                Developper dev = new Developper();
                dev.setUsername("John Doe developper");
                dev.setUser(user1);

                em.persist(dev);

                Developper dev2 = new Developper();
                dev2.setUsername("Jill Doe developper");
                dev2.setUser(user2);
                em.persist(dev2);
//                //  Création des projets
                Project project1 = new Project();
                project1.setProjectName("Gestion de projets");
                project1.setProjectDescription("Application de gestion de projets interne");
                project1.setProjectOwner(owner);
                project1.setThemes(List.of(javaTheme, springTheme));
                project1.setBudget(10025.5);

                Project project2 = new Project();
                project2.setProjectName("Site e-commerce");
                project2.setProjectDescription("Application de site de ventes en ligne");
                project2.setProjectOwner(owner);
                project2.setThemes(List.of(backendTheme));

                em.persist(project1);
                em.persist(project2);

                //Creation JobApplication
                List<JobApplication> jobs = new ArrayList<>();


                JobApplication jobApplication = new JobApplication();
                jobApplication.setProject(project1);
                jobs.add(jobApplication);
                dev.setJobApplications(jobs);

                // Commit
                em.getTransaction().commit();

                // Dev Repo
                DevelopperRepository devrepo = new DevelopperRepository(emf,Developper.class);

                for(Developper developper: devrepo.findAll()) {
                    System.out.println(developper);
                }

                //JobApplication repo

                JobApplicationRepository jobApplicationRepository = new JobApplicationRepository(emf,JobApplication.class);
                System.out.println(jobApplicationRepository.findByProjectName("Site e-commerce"));

                //ProjectOwnerRepository
                ProjectOwnerRepository projectOwnerRepository = new ProjectOwnerRepository(emf, ProjectOwner.class);
               for(ProjectOwner projectOwner: projectOwnerRepository.findAll()) {
                   System.out.println(projectOwner);
               }

               //ProjectRepo
                ProjectRepository projectRepository = new ProjectRepository(emf,Project.class);

               for(Project project: projectRepository.findByBudget(project1.getBudget())) {
                   System.out.println(project);
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