package com.od.eisgroup.service.impl;

import com.od.eisgroup.dao.repository.UserRepository;
import com.od.eisgroup.domain.dto.UserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Yanevskyy Igor i.yanevskyy@gmail.com.
 */
@RunWith(Parameterized.class)
public class ViewerProfilesUsersParametrizedTest {
    private UserRepository userRepository = mock(UserRepository.class);
    private ViewerProfilesUsers viewerProfiles = new ViewerProfilesUsers();
    private static UsersCreator usersCreator = new UsersCreator();
    private String searchPhrase;
    private List<UserDTO> usersDTO;

    public ViewerProfilesUsersParametrizedTest(String searchPhrase, List<UserDTO> usersDTO) {
        this.searchPhrase = searchPhrase;
        this.usersDTO = usersDTO;
        viewerProfiles.setUserRepository(userRepository);
        viewerProfiles.setModelMapper(new ModelMapper());
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                {"ivanov-koval", Arrays.asList(usersCreator.getUsersDTO().get(3), usersCreator.getUsersDTO().get(4),
                        usersCreator.getUsersDTO().get(5))},
                {"ivanova-kovaleva", Collections.singletonList(usersCreator.getUsersDTO().get(5))},
                {"man dur", Collections.singletonList(usersCreator.getUsersDTO().get(2))},
                {"dur ma", Arrays.asList(usersCreator.getUsersDTO().get(1), usersCreator.getUsersDTO().get(2))},
                {"dur hill", new ArrayList<>()},
                {" ", new ArrayList<>()},
                {"j-y c", Collections.singletonList(usersCreator.getUsersDTO().get(0))},
                {"me mu tri", Collections.singletonList(usersCreator.getUsersDTO().get(6))},
                {"-W", new ArrayList<>()},
                {"-k", Arrays.asList(usersCreator.getUsersDTO().get(3), usersCreator.getUsersDTO().get(4),
                        usersCreator.getUsersDTO().get(5))},
                {"- ", Arrays.asList(usersCreator.getUsersDTO().get(0),
                        usersCreator.getUsersDTO().get(1),
                        usersCreator.getUsersDTO().get(2),
                        usersCreator.getUsersDTO().get(3),
                        usersCreator.getUsersDTO().get(4),
                        usersCreator.getUsersDTO().get(5))},
                {"dur hil", Collections.singletonList(usersCreator.getUsersDTO().get(1))}};
        return Arrays.asList(data);
    }

    @Test
    public void displayFoundUsers() {
        when(userRepository.findAll()).thenReturn(usersCreator.getUsers());
        assertEquals(usersDTO, viewerProfiles.displayFoundUsers(searchPhrase));
    }
}
