package com.challenge.bricks.core.job;

import com.challenge.bricks.config.job.JobFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ProductJob {


    @Scheduled(cron = "${job.cron.executeJobUpdateCategoryList}", zone = "#{@commonConstantsZone}")
    public void executeJobUpdateCategoryList() {
        JobFactory.getJob(JobCatalog.JOB_UPDATE_CATEGORY_LIST).executeJob();
    }

}
