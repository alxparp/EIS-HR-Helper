package com.od.eisgroup.service.impl.job.listener;


import com.od.eisgroup.domain.dto.JobDTO;
import com.od.eisgroup.domain.dto.enums.JobState;
import com.od.eisgroup.service.api.JobService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The purpose of current class is tracking changes of the jobs. If at least one of them changes its condition,
 * appropriate method is called immediately. The class registers state of Jobs (IDLE, RUNNING) and also
 * how many times each of them was executed.
 *
 * @author Vadim Martsun
 * @version since 1.1
 */
@Slf4j
@Getter
@Setter
//@Service
public class EventJobListener implements JobListener {

    private static final String LISTENER_NAME = "EventJobListener";

    private List<JobDTO> jobs;

    @Autowired
    private StateManipulator manipulator;

    @Autowired
    private JobService jobService;

    @Override
    public String getName() {
        return LISTENER_NAME;
    }

    /**
     * The method is called by the Scheduler when a job is about to be executed.
     *
     * @param context - a context bundle containing handles
     *                to various environment information,
     *                that is given to a JobDetail instance.
     */
    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        String jobName = context.getJobDetail().getKey().getName();
        manipulator.setRunning(jobName);

        log.debug("Job {} has been started", jobName);
    }

    /**
     * Called by the Scheduler when a job was about to be executed
     * (an associated Trigger has occurred), but a TriggerListener vetoed it's execution.
     *
     * @param context - a context bundle containing handles
     *                to various environment information,
     *                that is given to a JobDetail instance.
     */
    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        String jobName = context.getJobDetail().getKey().getName();
        log.debug("Job {} has been vetoed", jobName);
    }

    /**
     * The method is called when a job has just been executed.
     *
     * @param context - a context bundle containing handles
     *                to various environment information,
     *                that is given to a JobDetail instance.
     * @param e       - an exception that can be thrown by a job to indicate to the Quartz Scheduler
     *                that an error occurred while executing, and whether or not the job requests to be re-fired immediately.
     */
    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException e) {
        String jobName = context.getJobDetail().getKey().getName();

        manipulator.increaseExecutionCount(jobName);
        manipulator.setIdle(jobName);

        log.debug("Job {} has been finished", jobName);
    }

    /**
     * An object which finds appropriate job in list and changes its condition.
     */
    @Service
    private class StateManipulator {

        private int getIndex(String jobInfoName) {
            JobDTO jobDTO = new JobDTO();
            jobDTO.setJobName(jobInfoName);
            return jobs.indexOf(jobDTO);
        }

        private void increaseExecutionCount(String jobInfoName) {
            int i = getIndex(jobInfoName);

            if (i != -1) {
                jobs.get(i).setExecutionCount(jobs.get(i).getExecutionCount() + 1);

                JobDTO jobToUpdate = jobs.get(i);
                jobService.update(jobToUpdate);
            }
        }

        private void setIdle(String jobInfoName) {
            int i = getIndex(jobInfoName);

            if (i != -1) {
                jobs.get(i).setState(JobState.IDLE);
            }
        }

        private void setRunning(String jobInfoName) {
            int i = getIndex(jobInfoName);

            if (i != -1) {
                jobs.get(i).setState(JobState.RUNNING);
            }
        }

    }
}


