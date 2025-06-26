package com.hb.cda.projetorm.repository.interfaces;

import com.hb.cda.projetorm.entity.Project;
import com.hb.cda.projetorm.entity.Status;
import com.hb.cda.projetorm.entity.Theme;

import java.time.LocalDate;
import java.util.List;

public interface ProjectRepository extends GenericRepository<Project, Integer> {
    List<Project> findByEndDate(LocalDate enDate);
    List<Project> findByBudget(Double budget);
    List<Project> findByStatus(Status status);
    List<Project> findByJobApplicationStatus(Status status);
    List<Project> findByProjectTheme(Theme theme);
}
