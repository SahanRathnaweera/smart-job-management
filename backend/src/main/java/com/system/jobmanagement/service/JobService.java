package com.system.jobmanagement.service;

import com.system.jobmanagement.entity.Job;
import com.system.jobmanagement.entity.User;
import com.system.jobmanagement.repository.JobRepository;
import com.system.jobmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public Job createJob(Job job, String companyEmail) {
        User company = userRepository.findByEmail(companyEmail)
                .orElseThrow(() -> new RuntimeException("Company user not found"));
        job.setCompany(company);
        return jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}
