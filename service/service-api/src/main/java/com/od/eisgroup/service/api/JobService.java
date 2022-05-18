package com.od.eisgroup.service.api;

import com.od.eisgroup.domain.dto.JobDTO;

import java.util.List;

/**
 * Interface to be implemented by Services that will
 * work with Job entity and JobDTO.
 *
 * @author Vadim Martsun
 * @version since 1.1
 */
public interface JobService {

    /**
     * Returns information about all jobs existed in the system, including only counters of executions
     *
     * @return a list which contains information about all jobs existed in the system
     */
    List<JobDTO> getAll();

    /**
     * Updates information about the particular job
     *
     * @param job - an instance of a JobDTO, information of which it is needed to update
     */
    void update(JobDTO job);

    /**
     * Returns jobName and its condition, including state and counter of executions
     *
     * @param jobId - id of job, information of which it is needed to obtain
     * @return an instance of JobDTO class which contains information about certain job existed in the system
     */
    JobDTO getJob(Long jobId);
}
