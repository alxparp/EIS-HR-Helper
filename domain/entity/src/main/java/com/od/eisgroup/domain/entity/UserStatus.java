package com.od.eisgroup.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * An enum for user status in {@link com.od.eisgroup.domain.entity.User} entity
 *
 * @author Dmitriy Osipov
 * @since 1.1
 */

@Getter
@AllArgsConstructor
public enum UserStatus {
    /**
     * "not available" users who can't do something in the system
     */
    NOT_AVAILABLE("Not available"),

    /**
     * Simple users to whom actions are available
     */
    AVAILABLE("Available");

    private final String value;
}
