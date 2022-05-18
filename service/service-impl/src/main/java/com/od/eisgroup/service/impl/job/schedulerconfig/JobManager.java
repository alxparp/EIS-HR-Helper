package com.od.eisgroup.service.impl.job.schedulerconfig;


import com.od.eisgroup.domain.dto.JobDTO;
import com.od.eisgroup.service.impl.job.pojos.Schedule;
import com.od.eisgroup.service.impl.job.sheduler.SimpleScheduler;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * Current interface has methods which should schedule a list of jobs, launching them concurrently.
 *
 * @author Vadim Martsun
 * @version since 1.1
 */
public interface JobManager {

    /**
     * Schedules a list of jobs concurrently
     *
     * @param scheduler         - instance of class Scheduler
     * @param jobs              - a list of JobDTO which contains information of jobs' conditions
     * @param schedule          - the dates when jobs should be started and stopped
     * @param repeatCount       - how many times a job should be executed. Use value -1 to repeat execution of a job infinitely
     * @param intervalInMinutes - interval according to which execution of a job should be repeated
     * @throws SchedulerException     -
     * @throws ClassNotFoundException - if the jobs' classes cannot be located
     */
    void scheduleJobs(SimpleScheduler scheduler, List<JobDTO> jobs, Schedule schedule,
                      int repeatCount, int intervalInMinutes) throws SchedulerException, ClassNotFoundException;


    /**
     * Schedules a list of jobs concurrently
     *
     * @param scheduler         - instance of class Scheduler
     * @param jobs              - a list of JobDTO which contains information of jobs' conditions
     * @param repeatCount       - how many times a job should be executed. Use value -1 to repeat execution of a job infinitely
     * @param intervalInMinutes - interval according to which execution of a job should be repeated
     * @throws SchedulerException
     * @throws ClassNotFoundException
     */
    void scheduleJobs(SimpleScheduler scheduler, List<JobDTO> jobs,
                      int repeatCount, int intervalInMinutes) throws SchedulerException, ClassNotFoundException;
}
