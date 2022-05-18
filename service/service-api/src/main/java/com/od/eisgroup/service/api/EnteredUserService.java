package com.od.eisgroup.service.api;

import com.od.eisgroup.domain.entity.User;

/**
 * This is interface for service which will work with already logged in user.
 * This service will allow to get users data (e.g. full name) and make some processing in future
 * (e.g. logout, etc.)
 *
 * @author Dmitriy Osipov
 * @since 1.2
 */

public interface EnteredUserService {

    User getCurrentUser();

    String getCurrentUserFullName();
}
