package com.college.users;

import com.college.ROLE;

public record UserDTO(
    String email,
    String name,
    ROLE role
) {
}
