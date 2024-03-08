package com.challenge.bricks.controller.job;


import com.challenge.bricks.config.job.JobFactory;
import com.challenge.bricks.core.job.JobCatalog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/job")
public class JobBackOfficeController {

    @PostMapping("/execute/{jobName}")
    public void executeJob(@PathVariable String jobName) {
        JobFactory.getJob(JobCatalog.valueOf(jobName)).executeJob();
    }

}
