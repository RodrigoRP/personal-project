package com.rodrigoramos.personalproject.dto;

import com.rodrigoramos.personalproject.model.User;
import lombok.Data;

@Data
public class UserDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String cpf;
    private String password;

    public UserDTO() {
    }

    public UserDTO(String firstName, String lastName, String email, String cpf, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cpf = cpf;
        this.password = password;
    }

    public UserDTO(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.cpf = user.getCpf();
    }


}
