package com.college.users;

import com.college.ROLE;
import com.college.courses.CourseEntity;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Users")
public class UserEntity implements UserDetails {

    @Id
    @Column(
            name = "Email"
    )
    private String email;
    @Column(
            name = "Password"
    )
    private String password;

    @Column(
            name = "Name"
    )
    private String name;

    @Column(
            name = "Role",
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private ROLE role;



    public UserEntity() {
    }

    public UserEntity(String email, String password, String name, ROLE role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }

    public ROLE getRole() {
        return role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(ROLE role) { this.role = role; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity that)) return false;
        return Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getPassword(), that.getPassword()) && Objects.equals(getName(), that.getName()) && getAuthorities() == that.getAuthorities();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), getName(), getAuthorities());
    }
}
