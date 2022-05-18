package com.od.eisgroup.ui.beans;

import com.od.eisgroup.domain.dto.RoleDTO;
import com.od.eisgroup.service.api.RoleService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Controller which dispatches UI requests to application to work with user Roles.
 *
 * @author Alice Klochkova
 * @since 1.1
 */
@Getter
@Setter
@Controller
public class RoleController {

    /**
     * Field which is setted when user press
     * View or Edit button in DataTable.
     */
    private RoleDTO selected;

    /**
     * Field that is initialized after Bean creation with method
     *
     * @see #init
     * using
     * @see #roleService
     */
    private List<RoleDTO> roleDTOS;

    /**
     * {@link com.od.eisgroup.service.api.RoleService} autowired Bean
     */
    @Autowired
    private RoleService roleService;

    /**
     * Method to get all Roles (DTOs).
     */
    @PostConstruct
    public void init() {
        roleDTOS = roleService.findAll();
    }
}
