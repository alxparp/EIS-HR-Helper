package com.od.eisgroup.ui.beans;

import com.od.eisgroup.domain.dto.JobDTO;
import com.od.eisgroup.service.api.JobService;
import com.od.eisgroup.service.impl.job.pojos.Schedule;
import com.od.eisgroup.service.impl.job.schedulerconfig.JobManager;
import com.od.eisgroup.service.impl.job.sheduler.SimpleScheduler;
import lombok.Getter;
import lombok.Setter;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.quartz.DateBuilder.dateOf;


/**
 * The controller of scheduler tab which contains information
 * about conditions of all jobs existed in the system.
 *
 * @author Vadim Martsun
 * @version since 1.1
 */
@Controller
@SessionScope
@Getter
@Setter
public class SchedulerController {

    private static final int TWENTY_FOUR_HOURS = 60 * 24;
    private static final int EXECUTION_COUNT = -1;

    @Autowired
    private SimpleScheduler simpleScheduler;

    @Autowired
    private JobManager jobManager;

    @Autowired
    private JobService jobService;

    private List<JobDTO> jobs;

    public SchedulerController() throws ClassNotFoundException, SchedulerException {
        jobs = jobService.getAll();
        simpleScheduler.initJobListener(jobs);

        /*Launch jobs at 00:00*/
        fireJobs(0, 0, 0);
    }

//    @PostConstruct
//    public void init() throws SchedulerException, ClassNotFoundException {
//        j
//    }

    private void fireJobs(int hours, int minutes, int seconds) throws SchedulerException, ClassNotFoundException {
        Schedule schedule = new Schedule(dateOf(hours, minutes, seconds));
        jobManager.scheduleJobs(simpleScheduler, jobs, schedule, EXECUTION_COUNT, TWENTY_FOUR_HOURS);
        simpleScheduler.launch();
    }
}

