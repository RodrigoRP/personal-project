package com.rodrigoramos.personalproject.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class UserDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String firstName;
    private String lastName;
    private String email;
    private String cpf;

    public UserDTO() {
    }


}
