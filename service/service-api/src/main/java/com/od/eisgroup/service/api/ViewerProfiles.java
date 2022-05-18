package com.od.eisgroup.service.api;

import com.od.eisgroup.domain.dto.UserDTO;

import java.util.List;

/**
 * Finds all users and finds users by search phrase.
 *
 * @author Yanevskyy Igor i.yanevskyy@gmail.com.
 * @since 1.2
 */
public interface ViewerProfiles {
    /**
     * Finds and displays all users whose the last name or the first name contains the search phrase. Or the search text
     * is in the last name and first name.
     *
     * @param searchPhrase phrase to search for.
     * @return collection  of found users.
     */
    List<UserDTO> displayFoundUsers(String searchPhrase);

    /**
     * Displays all users from DB.
     *
     * @return collection all users.
     */
    List<UserDTO> displayAllUsers();

}
