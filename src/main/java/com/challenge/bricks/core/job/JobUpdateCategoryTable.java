package com.challenge.bricks.core.job;

import com.challenge.bricks.config.job.IJobCatalog;
import com.challenge.bricks.config.job.Job;
import com.challenge.bricks.core.product.service.CategoryService;
import com.challenge.bricks.exception.BusinessException;
import com.challenge.bricks.exception.MessageCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
class JobUpdateCategoryTable implements Job {

    private final CategoryService categoryService;

    JobUpdateCategoryTable(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void executeJob() {
        log.debug("Execute process update category table started!");
        try {
            categoryService.updateCategoryTable();

        }catch (Exception e){
            log.error("Failed process update category table!");
            throw new BusinessException(MessageCode.ERROR_JOB_UPDATE_CATEGORY_TABLE);
        }
        log.debug("Execute process update category table end with exit!");
    }
    @Override
    public IJobCatalog name() {
        return JobCatalog.JOB_UPDATE_CATEGORY_LIST;
    }
}