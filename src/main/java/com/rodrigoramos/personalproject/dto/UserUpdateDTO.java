package com.rodrigoramos.personalproject.dto;

import lombok.Data;

@Data
public class UserUpdateDTO {

    private String firstName;
    private String lastName;
    private String email;

    public UserUpdateDTO() {
    }
}
