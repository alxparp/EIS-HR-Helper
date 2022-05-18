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
 * A class searches for employees that have approved:
 * Annual Leave, Day Off, Sick Leave, Parental Leave, Business trip, Unpaid Leave, Bereavement Leave,
 * Documented Sick Leave, Education Leave, Maternity Leave, Floating Holiday, Marriage Leave, Paternity Leave
 * at least for one day during the period starting from 20-th of current month till the end of month according to the BRA075.
 * If appropriate information is found, it generates drafts.
 *
 * @author Vadim Martsun
 * @version since 1.1
 */
@Slf4j
@Getter
@Setter
@Service
public class GenerateInvoiceDraftJob implements Job, InterruptableJob {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        log.debug("Job executed with {}", jobExecutionContext);
    }

    @Override
    public void interrupt() {
        throw new UnsupportedOperationException();
    }
}
