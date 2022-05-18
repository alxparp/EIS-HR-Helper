package com.od.eisgroup.service.impl.job.sheduler.impl;

import com.od.eisgroup.domain.dto.JobDTO;
import com.od.eisgroup.service.impl.job.listener.EventJobListener;
import com.od.eisgroup.service.impl.job.pojos.JobIdentity;
import com.od.eisgroup.service.impl.job.pojos.Schedule;
import com.od.eisgroup.service.impl.job.pojos.TriggerIdentity;
import com.od.eisgroup.service.impl.job.sheduler.SimpleScheduler;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * The class provides basic quartz functionality, which helps to schedule and launch jobs
 *
 * @author Vadim Martsun
 * @version since 1.1
 */
@Slf4j
@Getter
@Setter
//@Service
public class QuartzScheduler implements SimpleScheduler {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private EventJobListener eventJobListener;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initJobListener(List<JobDTO> jobs) {
        try {
            eventJobListener.setJobs(jobs);
            scheduler.getListenerManager().addJobListener(eventJobListener);
        } catch (SchedulerException e) {
            log.debug(e.getMessage());
        }
    }

    private String getJobName(Class jobClass) {
        String jobClassName = jobClass.getSimpleName();
        return jobClassName.substring(0, 1).toLowerCase() + jobClassName.substring(1);
    }

    private JobDetail getJobDetail(JobIdentity jobIdentity) {
        String jobName = getJobName(jobIdentity.getJobClass());

        JobKey jobKey = new JobKey(jobName, jobIdentity.getGroup());
        return newJob(jobIdentity.getJobClass()).withIdentity(jobKey).build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void scheduleJob(JobIdentity jobIdentity, TriggerIdentity triggerIdentity,
                            Schedule schedule, int repeatCount, int intervalInMinutes) throws SchedulerException {

        JobDetail jobDetail = getJobDetail(jobIdentity);
        Trigger trigger = newTrigger().withIdentity(triggerIdentity.getTriggerName(), triggerIdentity.getTriggerGroup())
                .startAt(schedule.getStartTime()).endAt(schedule.getEndTime())
                .withSchedule(simpleSchedule().withIntervalInMinutes(intervalInMinutes).withRepeatCount(repeatCount)).build();

        scheduler.scheduleJob(jobDetail, trigger);
        log.debug("Job {} has been scheduled", jobIdentity.getJobClass());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void scheduleJob(JobIdentity jobIdentity, TriggerIdentity triggerIdentity,
                            int repeatCount, int intervalInMinutes) throws SchedulerException {

        JobDetail jobDetail = getJobDetail(jobIdentity);
        Trigger trigger = newTrigger().withIdentity(triggerIdentity.getTriggerName(), triggerIdentity.getTriggerGroup())
                .startNow()
                .withSchedule(simpleSchedule().withIntervalInMinutes(intervalInMinutes).withRepeatCount(repeatCount)).build();

        scheduler.scheduleJob(jobDetail, trigger);
        log.debug("Job {} has been scheduled", jobIdentity.getJobClass());
    }

    @Override
    public void scheduleJob(JobIdentity jobIdentity, TriggerIdentity triggerIdentity) throws SchedulerException {
        JobDetail jobDetail = getJobDetail(jobIdentity);

        Trigger trigger = newTrigger().withIdentity(triggerIdentity.getTriggerName(), triggerIdentity.getTriggerGroup())
                .startNow().withSchedule(simpleSchedule().withRepeatCount(1).withIntervalInMinutes(1)).build();

        scheduler.scheduleJob(jobDetail, trigger);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void launch() throws SchedulerException {
        scheduler.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean interrupt(String jobName) throws SchedulerException {
        List<JobExecutionContext> currentlyExecuting = scheduler.getCurrentlyExecutingJobs();

        boolean response = false;
        for (JobExecutionContext jobExecutionContext : currentlyExecuting) {
            if (jobExecutionContext.getJobDetail().getKey().getName().equals(jobName)) {
                response = scheduler.interrupt(jobExecutionContext.getJobDetail().getKey());
            }
        }
        return response;
    }

}
