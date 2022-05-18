package com.od.eisgroup.service.impl;

import com.od.eisgroup.dao.repository.RoleRepository;
import com.od.eisgroup.domain.dto.RoleDTO;
import com.od.eisgroup.domain.entity.Role;
import com.od.eisgroup.service.api.RoleService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceImplTest {

    @InjectMocks
    private RoleService roleService = new RoleServiceImpl();

    @Mock
    private RoleRepository roleRepository;
    @Spy
    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void findAll() {
        Role role = new Role();
        role.setName("User");
        role.setPrivileges(Collections.emptyList());
        when(roleRepository.findAll()).thenReturn(Collections.singletonList(role));
        List<RoleDTO> roleDTOS = roleService.findAll();
        RoleDTO roleDTO = roleDTOS.get(0);

        Assert.assertEquals(roleDTO.getName(), role.getName());
    }
}