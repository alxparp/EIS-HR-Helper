package com.od.eisgroup.service.impl;/**
 * @author Yanevskyy Igor i.yanevskyy@gmail.com.
 */

import com.od.eisgroup.domain.dto.PrivilegeDTO;
import com.od.eisgroup.domain.dto.RoleDTO;
import com.od.eisgroup.domain.dto.UserDTO;
import com.od.eisgroup.domain.entity.Privilege;
import com.od.eisgroup.domain.entity.Role;
import com.od.eisgroup.domain.entity.User;
import com.od.eisgroup.domain.entity.UserStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @author Yanevskyy Igor i.yanevskyy@gmail.com.
 */
public class UsersCreator {
    private User user = createUser(1L, "Sasha", "Ivanov-Koval", "sasha@gmail.com");
    private User user1 = createUser(2L, "Sasha", "Ivanova-Koval", "sashaIv@gmail.com");
    private User user2 = createUser(3L, "Sasha", "Ivanova-Kovaleva", "sashaIvK@gmail.com");
    private User user3 = createUser(4L, "Abdurrahma ad-Dakhil Wahi", "Gus Dur", "mail1@gmail.com");
    private User user4 = createUser(5L, "Abdurrahman ad-Dakman Wahidur", "Gus Dur", "mail1@gmail.com");
    private User user5 = createUser(6L, "Jacques-Yves", "Cousteau", "mail1@gmail.com");
    private User user6 = createUser(7L, "Patrice Emery", "Lumumba", "mail1@gmail.com");
    private List<User> users = new ArrayList<>(Arrays.asList(user, user1, user2, user3, user4, user5, user6));

    private UserDTO userDTO = createUserDTO(1L, "Sasha", "Ivanov-Koval", "sasha@gmail.com");
    private UserDTO user1DTO = createUserDTO(2L, "Sasha", "Ivanova-Koval", "sashaIv@gmail.com");
    private UserDTO user2DTO = createUserDTO(3L, "Sasha", "Ivanova-Kovaleva", "sashaIvK@gmail.com");
    private UserDTO user3DTO = createUserDTO(4L, "Abdurrahma ad-Dakhil Wahi", "Gus Dur", "mail1@gmail.com");
    private UserDTO user4DTO = createUserDTO(5L, "Abdurrahman ad-Dakman Wahidur", "Gus Dur", "mail1@gmail.com");
    private UserDTO user5DTO = createUserDTO(6L, "Jacques-Yves", "Cousteau", "mail1@gmail.com");
    private UserDTO user6DTO = createUserDTO(7L, "Patrice Emery", "Lumumba", "mail1@gmail.com");
    private List<UserDTO> usersDTO = new ArrayList<>(Arrays.asList(user5DTO, user3DTO, user4DTO, userDTO,
            user1DTO, user2DTO, user6DTO));

    private UserDTO createUserDTO(long id, String userFirstName, String userLastName, String userEmail) {
        UserDTO user = new UserDTO();
        user.setId(id);
        user.setLastName(userLastName);
        user.setFirstName(userFirstName);
        user.setEmail(userEmail);
        user.setStatus(UserStatus.AVAILABLE);
        user.setRole(createRoleDTO());
        return user;
    }

    private Collection<PrivilegeDTO> createPrivilegeCollectionDTO() {
        PrivilegeDTO privilegeHR = new PrivilegeDTO();
        PrivilegeDTO privilegeMG = new PrivilegeDTO();
        privilegeHR.setId(1L);
        privilegeHR.setName("HR");
        privilegeMG.setId(2L);
        privilegeMG.setName("MG");
        Collection<PrivilegeDTO> privileges = new HashSet<>();
        privileges.add(privilegeHR);
        privileges.add(privilegeMG);
        return privileges;
    }

    private RoleDTO createRoleDTO() {
        RoleDTO role = new RoleDTO();
        role.setId(1L);
        role.setName("Admin");
        role.setPrivilegeDTOS(createPrivilegeCollectionDTO());
        return role;
    }

    private User createUser(long id, String userFirstName, String userLastName, String userEmail) {
        User user = new User();
        user.setId(id);
        user.setLastName(userLastName);
        user.setFirstName(userFirstName);
        user.setEmail(userEmail);
        user.setStatus(UserStatus.AVAILABLE);
        user.setRoles(createRole());
        return user;
    }

    private Collection<Privilege> createPrivilegeCollection() {
        Privilege privilegeHR = new Privilege();
        Privilege privilegeMG = new Privilege();
        privilegeHR.setId(1L);
        privilegeHR.setName("HR");
        privilegeMG.setId(2L);
        privilegeMG.setName("MG");
        Collection<Privilege> privileges = new HashSet<>();
        privileges.add(privilegeHR);
        privileges.add(privilegeMG);
        return privileges;
    }

    private List<Role> createRole() {
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setId(1L);
        role.setName("Admin");
        role.setPrivileges(createPrivilegeCollection());
        Role roleMG = new Role();
        roleMG.setId(2L);
        roleMG.setName("MG");
        roleMG.setPrivileges(createPrivilegeCollection());
        roles.add(role);
        roles.add(roleMG);
        return roles;
    }

    public List<User> getUsers() {
        return users;
    }


    public List<UserDTO> getUsersDTO() {
        return usersDTO;
    }
}
