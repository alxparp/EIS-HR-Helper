package com.od.eisgroup.ui.beans;

import com.od.eisgroup.service.api.EnteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * This controller allows to get current user full name for show it in the command blocks
 * of admin main page and main page.
 *
 * @author Dmitriy Osipov
 * @since 1.2
 */
@Controller
public class EnteredUserController {

    @Autowired
    private EnteredUserService service;

    public String getFullName() {
        return service.getCurrentUserFullName();
    }
}
