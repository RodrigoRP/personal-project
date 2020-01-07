package com.rodrigoramos.personalproject.service;

import com.rodrigoramos.personalproject.dto.UserDTO;
import com.rodrigoramos.personalproject.model.User;
import com.rodrigoramos.personalproject.repository.UserRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

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
        when(userRepository.save(user)).thenReturn(user);
        when(userConverter.convertEntityToDto(user)).thenReturn(expected);

       User actual = userService.save(user);

      // assertEquals(user, actual);

       //  verify(userConverter).convertDtoToEntity(userDTO);
         verify(userRepository).save(user);
        // verify(userConverter).convertEntityToDto(user);
    }

    @Test
    public void findAll() {
        List<User> userList = new ArrayList<User>();
        when(userRepository.findAll(any(Sort.class))).thenReturn(userList);

        List<User> returned = userService.findAll();

        ArgumentCaptor<Sort> sortArgument = ArgumentCaptor.forClass(Sort.class);
        verify(userRepository, times(1)).findAll(sortArgument.capture());

     //   verifyNoMoreInteractions(userRepository);

      //  Sort actualSort = sortArgument.getValue();
       // assertEquals(Sort.Direction.ASC, actualSort.getOrderFor("lastName").getDirection());

       // assertEquals(userList, returned);
    }
}