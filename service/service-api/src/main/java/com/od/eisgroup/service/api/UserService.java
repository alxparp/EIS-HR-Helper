package com.od.eisgroup.service.api;

import com.od.eisgroup.domain.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Map;
import java.util.Set;

public interface UserService extends UserDetailsService {
    User findByEmail(String email);

    User fromUserInfoMap(Map<String, Object> map);

    Set<GrantedAuthority> getAuthorities(User user);
}
