package com.system.jobmanagement.service;

import com.system.jobmanagement.entity.Application;
import com.system.jobmanagement.entity.ApplicationStatus;
import com.system.jobmanagement.entity.Job;
import com.system.jobmanagement.entity.User;
import com.system.jobmanagement.repository.ApplicationRepository;
import com.system.jobmanagement.repository.JobRepository;
import com.system.jobmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    public Application applyForJob(Long jobId, String studentEmail, String resumeUrl) {
        User student = userRepository.findByEmail(studentEmail)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        if (applicationRepository.existsByStudentIdAndJobId(student.getId(), jobId)) {
            throw new RuntimeException("You have already applied for this job!");
        }

        Application application = Application.builder()
                .student(student)
                .job(job)
                .resumeUrl(resumeUrl)
                .status(ApplicationStatus.PENDING)
                .build();

        return applicationRepository.save(application);
    }

    public List<Application> getStudentApplications(String studentEmail) {
        User student = userRepository.findByEmail(studentEmail)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return applicationRepository.findByStudentId(student.getId());
    }

    public List<Application> getJobApplications(Long jobId) {
        return applicationRepository.findByJobId(jobId);
    }

    public Application updateStatus(Long applicationId, ApplicationStatus status) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        application.setStatus(status);
        return applicationRepository.save(application);
    }
}
