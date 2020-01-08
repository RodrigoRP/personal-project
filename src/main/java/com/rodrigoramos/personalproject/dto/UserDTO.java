package com.rodrigoramos.personalproject.dto;

import com.rodrigoramos.personalproject.model.User;
import com.rodrigoramos.personalproject.service.validation.UserSave;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@UserSave
public class UserDTO {

    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min=2, max=20, message="O tamanho deve ser entre 2 e 20 caracteres")
    private String firstName;

    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min=2, max=20, message="O tamanho deve ser entre 2 e 20 caracteres")
    private String lastName;

    @NotEmpty(message="Preenchimento obrigatório")
    @Email(message="Email inválido")
    private String email;

    @NotEmpty(message="Preenchimento obrigatório")
    private String cpf;

    @NotEmpty(message="Preenchimento obrigatório")
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
