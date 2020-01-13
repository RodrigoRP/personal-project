package com.rodrigoramos.personalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rodrigoramos.personalproject.model.enums.Profile;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    @JsonIgnore
    private String cpf;
    @JsonIgnore
    private String password;
    private Boolean admin;

    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name="PROFILES")
    private Set<Integer> profiles = new HashSet<>();

    public User() {
        addPerfil(Profile.USER);
    }

    public User(Long id, String firstName, String lastName, String email, String cpf, String password, Boolean admin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cpf = cpf;
        this.password = password;
        this.admin = Boolean.FALSE;
        addPerfil(Profile.USER);
    }

    public Set<Profile> getProfiles() {
        return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Profile profile) {
        profiles.add(profile.getCod());
    }

}
