package com.system.jobmanagement.controller;

import com.system.jobmanagement.entity.Application;
import com.system.jobmanagement.entity.ApplicationStatus;
import com.system.jobmanagement.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/apply")
    public ResponseEntity<Application> applyForJob(
            @RequestParam Long jobId,
            @RequestParam String studentEmail,
            @RequestParam(required = false) String resumeUrl) {
        return ResponseEntity.ok(applicationService.applyForJob(jobId, studentEmail, resumeUrl));
    }

    @GetMapping("/student")
    public ResponseEntity<List<Application>> getStudentApplications(@RequestParam String studentEmail) {
        return ResponseEntity.ok(applicationService.getStudentApplications(studentEmail));
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<Application>> getJobApplications(@PathVariable Long jobId) {
        return ResponseEntity.ok(applicationService.getJobApplications(jobId));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Application> updateStatus(
            @PathVariable Long id,
            @RequestParam ApplicationStatus status) {
        return ResponseEntity.ok(applicationService.updateStatus(id, status));
    }
}
