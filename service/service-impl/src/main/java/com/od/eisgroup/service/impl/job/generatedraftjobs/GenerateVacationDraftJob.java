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
 * System searches for employees that have available annual leave (
 * number of days according to the selected Location(s)) >= 50% at 1-st of May and 1-st of September (in current year)
 * according to the BRA075. If appropriate information is found, it generates drafts.
 *
 * @author Vadim Martsun
 * @version since 1.1
 */
@Slf4j
@Getter
@Setter
@Service
public class GenerateVacationDraftJob implements Job, InterruptableJob {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        log.debug("Job executed with {}", jobExecutionContext);
    }

    @Override
    public void interrupt() {
        throw new UnsupportedOperationException();
    }
}
