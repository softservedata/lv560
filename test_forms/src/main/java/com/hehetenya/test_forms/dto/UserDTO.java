package com.hehetenya.test_forms.dto;

import com.hehetenya.test_forms.entity.Role;

import java.util.Objects;

public class UserDTO {
    private String login;
    private String password;
    private Role role;

    public UserDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public UserDTO(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(login, userDTO.login) && Objects.equals(password, userDTO.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, role);
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
