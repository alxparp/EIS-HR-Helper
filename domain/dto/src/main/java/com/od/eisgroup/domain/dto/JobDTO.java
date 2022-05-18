package com.od.eisgroup.domain.dto;

import com.od.eisgroup.domain.dto.enums.JobState;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Current DTO contains information about jobs including their names, counter of executions and state.
 * It is necessary for depicting information of all jobs, existed in the system, on corresponding web page.
 *
 * @author Vadim Martsun
 * @version since 1.1
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "jobName")
@ToString
public class JobDTO implements Serializable {

    public static final long serialVersionUID = 97L;

    private Long id;
    /**
     * Represents name of a job which is unique
     */
    @Size(max = 255)
    @NotNull
    String jobName;

    /**
     * Represents counter of job's executions
     */
    private Long executionCount;

    /**
     * Represents condition of a job (IDLE or RUNNING)
     */
    private JobState state;
}
