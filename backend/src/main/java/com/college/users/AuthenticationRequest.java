package com.college.users;

public record AuthenticationRequest(
    String email,
    String password
) {
}
