package com.od.eisgroup.domain.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Current entity contains information about jobs including their names and counter of executions.
 * According to specification, the counter of executions must not be reset, consequently, it is necessary to store
 * this information in db in case if the server is restarted, all required information of jobs will be successfully restored.
 *
 * @author Vadim Martsun
 * @version since 1.1
 */

@Entity
@Table(name = "Jobs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "jobName")
@ToString
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Represents unique name of a job.
     */
    @Column(unique = true)
    private String jobName;

    /**
     * Represents counter of job's executions
     */
    private Long executionCount;
}
