package com.od.eisgroup.service.api;

import com.od.eisgroup.domain.dto.RoleDTO;
import com.od.eisgroup.domain.entity.Role;

import java.util.List;

/**
 * Interface to be implemented by Services that wish
 * to work with Role entity and RoleDTO.
 *
 * @author Alice Klochkova
 * @since 1.1
 */

public interface RoleService {
    /**
     * Method to be implemented to get all Roles
     * and convert to RoleDROs
     */
    List<RoleDTO> findAll();

    Role findByName(String roleName);
}
