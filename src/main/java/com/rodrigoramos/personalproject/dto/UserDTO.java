package com.rodrigoramos.personalproject.dto;

import com.rodrigoramos.personalproject.model.User;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class UserDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String firstName;
    private String lastName;
    private String email;
    private String cpf;

    public UserDTO() {
    }

    public User ConvertDtoToEntity(){
        return new User(firstName, lastName, email, cpf);
    }
}
