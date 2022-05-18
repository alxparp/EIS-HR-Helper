package com.od.eisgroup.service.impl.job.schedulerconfig.impl;

import com.od.eisgroup.domain.dto.JobDTO;
import com.od.eisgroup.service.impl.job.pojos.JobIdentity;
import com.od.eisgroup.service.impl.job.pojos.Schedule;
import com.od.eisgroup.service.impl.job.pojos.TriggerIdentity;
import com.od.eisgroup.service.impl.job.schedulerconfig.JobManager;
import com.od.eisgroup.service.impl.job.sheduler.SimpleScheduler;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Current class implements JobManager interface and
 * has methods which schedule a list of jobs, launching them concurrently.
 *
 * @author Vadim Martsun
 * @version since 1.1
 */
@Slf4j
@Getter
@Setter
@Component
public class QuartzJobManager implements JobManager {

    private static final String JOB_PACKAGE = "com.od.eisgroup.service.impl.jobs.generateDraftJobs";

    private static final String JOB_GROUP = "GenerateDraftJobs";

    private static final String TRIGGER_GROUP = "QuartzJobTriggers";

    private List<JobIdentity> jobIdentities = new ArrayList<>();

    private List<TriggerIdentity> triggers = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void scheduleJobs(SimpleScheduler scheduler, List<JobDTO> jobs, Schedule schedule,
                             int repeatCount, int intervalInMinutes) throws SchedulerException, ClassNotFoundException {

        configJobs(jobs);
        for (int i = 0; i < jobs.size(); i++)
            scheduler.scheduleJob(jobIdentities.get(i), triggers.get(i), schedule, repeatCount, intervalInMinutes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void scheduleJobs(SimpleScheduler scheduler, List<JobDTO> jobs,
                             int repeatCount, int intervalInMinutes) throws SchedulerException, ClassNotFoundException {

        configJobs(jobs);
        for (int i = 0; i < jobs.size(); i++)
            scheduler.scheduleJob(jobIdentities.get(i), triggers.get(i), repeatCount, intervalInMinutes);
    }

    /**
     * Creates instances of all jobs available in input list and creates appropriate triggers for each of them.
     * To avoid errors during execution of current method, names of job must coincide with simple names of their classes
     * but they should be started from letter in lower case.
     *
     * @param jobs - list oj jobs, instances of which should be created
     * @throws ClassNotFoundException - if the jobs' classes cannot be located
     */
    private void configJobs(List<JobDTO> jobs) throws ClassNotFoundException {
        for (JobDTO job : jobs) {
            String jobClassName = job.getJobName().substring(0, 1).toUpperCase()
                    + job.getJobName().substring(1);

            Class jobClass = Class.forName(JOB_PACKAGE + "." + jobClassName);

            jobIdentities.add(new JobIdentity(jobClass, JOB_GROUP));
            triggers.add(new TriggerIdentity(job.getJobName() + " " + "Trigger", TRIGGER_GROUP));
        }
    }

}

