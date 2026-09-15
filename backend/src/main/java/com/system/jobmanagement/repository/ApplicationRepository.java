package com.system.jobmanagement.repository;

import com.system.jobmanagement.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByStudentId(Long studentId);
    List<Application> findByJobId(Long jobId);
    Boolean existsByStudentIdAndJobId(Long studentId, Long jobId);
}
