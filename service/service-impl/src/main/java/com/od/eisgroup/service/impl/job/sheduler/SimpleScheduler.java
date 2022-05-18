package com.od.eisgroup.service.impl.job.sheduler;

import com.od.eisgroup.domain.dto.JobDTO;
import com.od.eisgroup.service.impl.job.pojos.JobIdentity;
import com.od.eisgroup.service.impl.job.pojos.Schedule;
import com.od.eisgroup.service.impl.job.pojos.TriggerIdentity;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * The interface provides basic quartz functionality, which helps to schedule and launch jobs
 *
 * @author Vadim Martsun
 * @version since 1.1
 */
public interface SimpleScheduler {

    /**
     * Initialize jobListener instance and adds it to scheduler instance.
     */
    void initJobListener(List<JobDTO> jobs);

    /**
     * Schedules a job
     *
     * @param jobIdentity       - information about job
     * @param triggerIdentity   - information about trigger
     * @param schedule          - the dates when a job should be started and stopped
     * @param repeatCount       - how many times a job should be executed. Use value -1 to repeat execution of a job infinitely
     * @param intervalInMinutes - interval according to which execution of a job should be repeated
     */
    void scheduleJob(JobIdentity jobIdentity, TriggerIdentity triggerIdentity,
                     Schedule schedule, int repeatCount, int intervalInMinutes) throws SchedulerException;

    /**
     * Schedules a job. Execution of a job starts immediately
     *
     * @param jobIdentity       - information about job
     * @param triggerIdentity   - information about trigger
     * @param repeatCount       - how many times a job should be executed. Use value -1 to repeat execution of a job infinitely
     * @param intervalInMinutes - interval according to which execution of a job should be repeated
     */
    void scheduleJob(JobIdentity jobIdentity, TriggerIdentity triggerIdentity,
                     int repeatCount, int intervalInMinutes) throws SchedulerException;

    /**
     * Schedules a job. Execution of a job starts immediately and only one time
     *
     * @param jobIdentity     - information about job
     * @param triggerIdentity - information about trigger
     */
    void scheduleJob(JobIdentity jobIdentity, TriggerIdentity triggerIdentity) throws SchedulerException;

    /**
     * Runs all registered jobs.
     */
    void launch() throws SchedulerException;

    /**
     * Interrupts execution of a job
     *
     * @param jobName - name of job which execution should be interrupted
     * @return true - if job's execution was interrupted, otherwise - false
     */
    boolean interrupt(String jobName) throws SchedulerException;

}
