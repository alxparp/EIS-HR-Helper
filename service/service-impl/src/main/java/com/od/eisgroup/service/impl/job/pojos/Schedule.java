package com.od.eisgroup.service.impl.job.pojos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * The class contains information about the date when a job should be started and
 * the date when job should be stopped. EndTime is optional.
 *
 * @author Vadim Martsun
 * @version since 1.1
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Schedule {

    /**
     * A date when a particular job should be started
     */
    @NotNull
    Date startTime;

    /**
     * A date when a particular job should be stopped
     * Not required property
     */
    Date endTime;

    public Schedule(Date startTime) {
        this.startTime = startTime;
    }

}
