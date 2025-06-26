package com.hb.cda.projetorm.repository.interfaces;

import com.hb.cda.projetorm.entity.JobApplication;

import java.util.List;

public interface JobApplicationRepository extends GenericRepository<JobApplication, Integer> {
    List<JobApplication> findByProjectName(String projectName);
}
