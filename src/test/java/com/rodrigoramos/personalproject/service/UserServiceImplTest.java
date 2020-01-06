package com.rodrigoramos.personalproject.service;

import com.rodrigoramos.personalproject.dto.UserDTO;
import com.rodrigoramos.personalproject.model.User;
import com.rodrigoramos.personalproject.repository.UserRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserServiceImpl userConverter;

    @Test
    public void shouldReturnAUserWhenISaveWithSuccess() {
        final UserDTO userDTO = new UserDTO(RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10));
        final UserDTO expected = new UserDTO(userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getEmail(),
                userDTO.getCpf());
        final User user = new User(null, userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getEmail(),
                userDTO.getCpf(),
                null);

        when(userConverter.convertDtoToEntity(userDTO)).thenReturn(user);
        when(userConverter.save(user)).thenReturn(user);
        when(userConverter.convertEntityToDto(user)).thenReturn(expected);

       User actual = userService.save(user);

      // assertEquals(user, actual);

        //verify(userConverter).convertDtoToEntity(userDTO);
         verify(userRepository).save(user);
         //verify(userConverter).convertEntityToDto(user);
    }
}