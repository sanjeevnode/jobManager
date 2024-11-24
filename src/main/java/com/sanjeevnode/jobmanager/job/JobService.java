package com.sanjeevnode.jobmanager.job;


import java.util.List;

public interface JobService {

    String createJob(Job job);

    List<Job> findAll();

    Job getJobById(Long id);

    boolean deleteJobById(Long id);

    Job updateJobById(Long id, Job job);

}
