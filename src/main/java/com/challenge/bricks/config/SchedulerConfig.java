package com.challenge.bricks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.*;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.time.ZoneId;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableScheduling
public class SchedulerConfig implements SchedulingConfigurer{
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(this.taskExecutor());
    }
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(10);
    }

    @Bean("commonConstantsZone")
    public String commonConstantsZone() {
        return  ZoneId.of("America/Argentina/Buenos_Aires").getId();
    }

}
