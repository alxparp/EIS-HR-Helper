package com.od.eisgroup.service.impl;

import com.od.eisgroup.domain.dto.UserDTO;

import java.util.Comparator;

/**
 * Sorts the userDTO list. First sorts by the user's last name and if they equals,
 * then sorts by user's first name.
 *
 * @author Yanevskyy Igor i.yanevskyy@gmail.com.
 * @since 1.2
 */
public class UserProfileComparator implements Comparator<UserDTO> {
    @Override
    public int compare(UserDTO o1, UserDTO o2) {
        int result = o1.getLastName().compareTo(o2.getLastName());
        return result != 0 ? result : o1.getFirstName().compareTo(o2.getFirstName());
    }
}
