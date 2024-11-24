package com.sanjeevnode.jobmanager.job.impl;

import com.sanjeevnode.jobmanager.job.Job;
import com.sanjeevnode.jobmanager.job.JobRepository;
import com.sanjeevnode.jobmanager.job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    final private JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public String createJob(Job job) {
        jobRepository.save(job);
        return "Job added successfully";
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        return jobRepository.findById(id).map(job -> {
            jobRepository.delete(job);
            return true;
        }).orElse(false);
    }

    @Override
    public Job updateJobById(Long id, Job job) {
        return jobRepository.findById(id).map(existingJob -> {
            existingJob.setTitle(job.getTitle());
            existingJob.setDescription(job.getDescription());
            existingJob.setMinSalary(job.getMinSalary());
            existingJob.setMaxSalary(job.getMaxSalary());
            existingJob.setLocation(job.getLocation());
            return jobRepository.save(existingJob);
        }).orElse(null);
    }
}
