package com.od.eisgroup.service.impl;

import com.od.eisgroup.dao.repository.UserRepository;
import com.od.eisgroup.domain.entity.Privilege;
import com.od.eisgroup.domain.entity.Role;
import com.od.eisgroup.domain.entity.User;
import com.od.eisgroup.service.api.RoleService;
import com.od.eisgroup.service.api.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = findByEmail(email);
        Set<GrantedAuthority> roles = getAuthorities(user);

        log.debug("Adding authorities {} was to User {}", roles, user.getEmail());
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                        user.getPassword(), user.isEnabled(),
                        true, true, true, roles);
    }

    @Override
    public User fromUserInfoMap(Map<String, Object> map) {
        String email = "email";
        String givenName = "given_name";
        String familyName = "family_name";
        if (findByEmail((String) map.get(email)) != null) {
            User user = findByEmail((String) map.get(email));
            log.debug("User {} was in database", user);
            user.setFirstName((String) map.get(givenName));
            user.setLastName((String) map.get(familyName));
            userRepository.save(user);
            return user;
        } else {
            User user = new User();
            user.setFirstName((String) map.get(givenName));
            user.setLastName((String) map.get(familyName));
            user.setEmail((String) map.get(email));
            user.setRoles(Collections.singletonList(roleService.findByName("Demo user")));
            user.setEnabled(true);
            userRepository.save(user);
            log.debug("User {} was not in database. Created new one.", user);
            return user;
        }
    }

    @Override
    public User findByEmail(String email) {
        log.debug("Searching {} user was in database", email);
        return userRepository.findByEmail(email);
    }

    @Override
    public Set<GrantedAuthority> getAuthorities(User user) {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (Role role : user.getRoles()) {
            roles.add(new SimpleGrantedAuthority(role.getAuthority()));
            for (Privilege privilege : role.getPrivileges()) {
                roles.add(new SimpleGrantedAuthority(privilege.getAuthority()));
            }
        }
        log.debug("Getting all authorities of {}.", user);
        return roles;

    }
}
