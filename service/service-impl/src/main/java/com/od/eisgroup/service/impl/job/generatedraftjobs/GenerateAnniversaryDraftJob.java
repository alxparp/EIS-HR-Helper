package com.od.eisgroup.service.impl.job.generatedraftjobs;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.quartz.InterruptableJob;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * A class searches for employees' Hire Date (DD/MM)
 * that will have anniversary in future or had in the past according to the BRA075.
 * If appropriate events are found, it generates drafts.
 *
 * @author Vadim Martsun
 * @version since 1.1
 */
@Slf4j
@Getter
@Setter
@Service
public class GenerateAnniversaryDraftJob implements Job, InterruptableJob {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        log.debug("Job executed with {}", jobExecutionContext);
    }

    @Override
    public void interrupt() {
        throw new UnsupportedOperationException();
    }
}
