package com.od.eisgroup.service.impl;

import com.od.eisgroup.dao.repository.UserRepository;
import com.od.eisgroup.domain.dto.UserDTO;
import com.od.eisgroup.domain.entity.User;
import com.od.eisgroup.service.api.ViewerProfiles;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Takes all user list in the DAO. Sorts the list by comparator "UserProfileComparator" and return to controller.
 * And finds into the users list who names contains search phrase.
 *
 * @author Yanevskyy Igor i.yanevskyy@gmail.com.
 * @since 1.2
 */
@Getter
@Setter
@Service
@NoArgsConstructor
public class ViewerProfilesUsers implements ViewerProfiles {
    /**
     * DAO entity
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * The entity user to userDTO converter.
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * If the search phrase contain two parts who separated by space, then first part that before the space,
     * should equals the first name or last name.
     *
     * @param searchPhrase phrase to search for.
     * @return found user list.
     */
    @Override
    public List<UserDTO> displayFoundUsers(String searchPhrase) {
        List<UserDTO> users = displayAllUsers();
        List<UserDTO> foundUsers = new ArrayList<>();
        if (users.isEmpty()) {
            return foundUsers;
        }

        String[] partsSearchPhrase = searchPhrase.trim().split(" ");
        foundUsers = findUserListByPhrase(partsSearchPhrase, users);
        return foundUsers;
    }

    @Override
    public List<UserDTO> displayAllUsers() {
        List<UserDTO> usersDTO = convertToDto(userRepository.findAll());
        usersDTO.sort(new UserProfileComparator());
        return usersDTO;
    }

    /**
     * Returns user list who have a "search text" in the last name or in the first name.
     *
     * @return found users.
     */
    private List<UserDTO> findUserListByPhrase(String[] searchPhrases, List<UserDTO> users) {
        List<UserDTO> foundUsers = new ArrayList<>();
        for (UserDTO user : users) {
            String userName = user.getFirstName().toLowerCase() + " " + user.getLastName().toLowerCase();
            int countReplace = counterReplacePhrase(userName, searchPhrases);
            if (searchPhrases.length == countReplace) {
                foundUsers.add(user);
            }
        }

        return foundUsers;
    }

    /**
     * Counts how many parts of the search phrase are contained in the full (first name + last name) of the user name.
     *
     * @param userName name of users
     * @param searchPhrases phrases for searching
     *
     * @return Number of contained parts
     */
    private int counterReplacePhrase(String userName, String[] searchPhrases) {
        int countReplace = 0;
        int lengthName = userName.length();
        for (String partPhrase : searchPhrases) {
            if (partPhrase.contains("-") && userName.contains("-")) {
                partPhrase = takeOutPartWithDash(userName, partPhrase);
            }
            userName = userName.replace(partPhrase.toLowerCase(), "");
            if (userName.length() < lengthName) {
                lengthName = userName.length();
                countReplace++;
            } else {
                return countReplace;
            }
        }
        return countReplace;
    }

    /**
     * If all parts of the name contain all parts of the phrase.
     * At the same time observing the order of the 1st in the 1st, 2nd in the 2nd, then return true.
     *
     * @param fragmentWithDash a phrase containing a dash.
     * @param partsName        a part name containing a dash.
     * @return result of comparison.
     */
    private boolean containsPhrases(String[] fragmentWithDash, String[] partsName) {
        if (fragmentWithDash.length != partsName.length) {
            return false;
        }
        for (int i = 0; i < fragmentWithDash.length; i++) {
            if (partsName[i].length() < fragmentWithDash[i].length()) {
                return false;
            } else {
                if (!fragmentWithDash[i].equals(partsName[i].substring(0, fragmentWithDash[i].length()))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Finds the first or last name part with a dash that matches the search phrase with a dash.
     * According to the ticket EISHR-30. If search value is Ivanov-Koval, I expect System to show as search result
     * Ivanov-Koval and Ivanova-Koval.
     * If a match is found, returns part of the name. If not, then part of the phrase.
     *
     * @param userName   The full of the user name.
     * @param partPhrase The search phrase.
     * @return Part of user name or search phrase.
     */
    private String takeOutPartWithDash(String userName, String partPhrase) {
        String[] partsName = userName.split(" ");
        for (String part : partsName) {
            if (part.contains("-") && containsPhrases(partPhrase.split("-"), part.split("-"))) {
                return part;
            }
        }
        return partPhrase;
    }

    /**
     * Converts the user list to userRepository list.
     *
     * @param users list with users.
     * @return userRepository list
     */
    private List<UserDTO> convertToDto(Iterable<User> users) {
        List<UserDTO> usersDTO = new ArrayList<>();
        if (users == null) {
            return usersDTO;
        } else {
            for (User user : users) {
                usersDTO.add(modelMapper.map(user, UserDTO.class));
            }
        }
        return usersDTO;
    }
}
