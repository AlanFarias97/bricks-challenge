package com.challenge.bricks.config.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobFactory {

    private static List<Job> jobs = null;

    @Autowired
    public JobFactory(List<Job> jobs) {
        this.jobs = jobs;
    }

    public static Job getJob(IJobCatalog jobName) {
        return jobs.stream().filter(job ->
                job.name().name().equals(jobName.name())).findFirst().orElse(null);
    }

}
