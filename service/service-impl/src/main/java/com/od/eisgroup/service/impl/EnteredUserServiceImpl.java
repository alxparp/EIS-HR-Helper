package com.od.eisgroup.service.impl;

import com.od.eisgroup.domain.entity.User;
import com.od.eisgroup.service.api.EnteredUserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * This is an implementation of EnteredUserService interface
 *
 * @author Dmitriy Osipov
 * @since 1.2
 */

@Service
public class EnteredUserServiceImpl implements EnteredUserService {

    @Override
    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public String getCurrentUserFullName() {
        User currentUser = this.getCurrentUser();
        return String.format("%s %s", currentUser.getFirstName(), currentUser.getLastName());
    }
}
