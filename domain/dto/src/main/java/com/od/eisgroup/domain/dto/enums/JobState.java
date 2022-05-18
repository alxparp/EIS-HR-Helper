package com.od.eisgroup.domain.dto.enums;

/**
 * Represents an objects that holds conditions in which each job can be.
 * IDLE - job is not executing
 * RUNNING - job is executing
 */
public enum JobState {

    /**
     * Job is not running
     */
    IDLE,

    /**
     * Job is running
     */
    RUNNING
}
