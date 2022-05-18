package com.od.eisgroup.service.impl;

import com.od.eisgroup.dao.repository.JobRepository;
import com.od.eisgroup.domain.dto.JobDTO;
import com.od.eisgroup.domain.entity.Job;
import com.od.eisgroup.service.api.JobService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Service which implements {@link com.od.eisgroup.service.api.JobService} to work with Job entities
 * and JobDTOs.
 *
 * @author Vadim Martsun
 * @since 1.1
 */
@Slf4j
//@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<JobDTO> getAll() {
        log.debug("Searching for all Jobs in database");
        List<JobDTO> jobs = new ArrayList<>();
        jobRepository.findAll().forEach(job -> jobs.add(convertToDTO(job)));
        return jobs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(JobDTO jobDTO) {
        Job jobToUpdate = convetToJob(jobDTO);
        jobRepository.save(jobToUpdate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobDTO getJob(Long jobId) {
        return jobRepository.findById(jobId)
                .map(this::convertToDTO)
                .orElse(null);
    }

    /**
     * Method to convert Job Entity to JobDTO via {@link org.modelmapper.ModelMapper}
     */
    private JobDTO convertToDTO(Job job) {
        log.debug("Converting {} to JobDTO", job);
        return modelMapper.map(job, JobDTO.class);
    }

    /**
     * Method to convert JobDTO to Job entity via {@link org.modelmapper.ModelMapper}
     */
    private Job convetToJob(JobDTO jobDTO) {
        log.debug("Converting {} to Job", jobDTO);
        return modelMapper.map(jobDTO, Job.class);
    }
}
