package com.od.eisgroup.service.impl;

import com.od.eisgroup.dao.repository.UserRepository;
import com.od.eisgroup.domain.dto.UserDTO;
import org.junit.Assert;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Yanevskyy Igor i.yanevskyy@gmail.com.
 */
public class ViewerProfilesUsersTest {
    private UserRepository userRepository = mock(UserRepository.class);
    private ViewerProfilesUsers viewerProfiles = new ViewerProfilesUsers();
    private UsersCreator usersCreator = new UsersCreator();


    @Test
    public void displayAllUsers() {
        viewerProfiles.setUserRepository(userRepository);
        viewerProfiles.setModelMapper(new ModelMapper());
        when(userRepository.findAll()).thenReturn(usersCreator.getUsers());
        List<UserDTO> usersDTOResult = viewerProfiles.displayAllUsers();
        for (int i = 0; i < usersDTOResult.size(); i++) {
            Assert.assertEquals(usersCreator.getUsersDTO().get(i).getId(), usersDTOResult.get(i).getId());
        }
    }
}