package com.od.eisgroup.service.impl;

import com.od.eisgroup.dao.repository.RoleRepository;
import com.od.eisgroup.domain.dto.PrivilegeDTO;
import com.od.eisgroup.domain.dto.RoleDTO;
import com.od.eisgroup.domain.entity.Privilege;
import com.od.eisgroup.domain.entity.Role;
import com.od.eisgroup.service.api.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Service which implements {@link com.od.eisgroup.service.api.RoleService} to work with Role entities
 * and RoleDTOs.
 *
 * @author Alice Klochkova
 * @since 1.1
 */
@Slf4j
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    /**
     * {@link org.modelmapper.ModelMapper} helps to convert Entity to DTO and vice versa.
     */
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Method implemented from {@link com.od.eisgroup.service.api.RoleService} to get all Roles
     * and convert to RoleDROs
     */
    public List<RoleDTO> findAll() {
        log.debug("Searching for all Roles in database.");
        List<RoleDTO> roleDTOS = new ArrayList<>();
        for (Role role : roleRepository.findAll()) {
            List<PrivilegeDTO> privilegeDTOS = new ArrayList<>();
            for (Privilege privilege : role.getPrivileges()) {
                privilegeDTOS.add(convertToDTO(privilege));
            }
            RoleDTO roleDTO = convertToDTO(role);
            roleDTO.setPrivilegeDTOS(privilegeDTOS);
            roleDTOS.add(roleDTO);
        }
        return roleDTOS;
    }

    /**
     * Method to convert Role Entity to RoleDTO via {@link org.modelmapper.ModelMapper}
     */
    private RoleDTO convertToDTO(Role role) {
        log.debug("Converting {} to DTO.", role);
        return modelMapper.map(role, RoleDTO.class);
    }

    @Override
    public Role findByName(String roleName) {
        return roleRepository.findByName(roleName);
    }

    private PrivilegeDTO convertToDTO(Privilege privilege) {
        log.debug("Converting {} to DTO.", privilege);
        return modelMapper.map(privilege, PrivilegeDTO.class);
    }
}
