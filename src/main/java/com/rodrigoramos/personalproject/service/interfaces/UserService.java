package com.rodrigoramos.personalproject.service.interfaces;

import com.rodrigoramos.personalproject.dto.UserDTO;
import com.rodrigoramos.personalproject.model.User;

public interface UserService {

    User save(User user);

    User convertDtoToEntity(UserDTO dto);
}
