package com.college.users;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }
    @GetMapping("/users")
    public List<UserDTO> getAllUsers()
    {
        return service.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public UserDTO getUsersById(@PathVariable("id") String email)
    {
        return service.getUserById(email);
    }

    @DeleteMapping
    public void deleteUser(@NonNull @RequestParam("id") String email)
    {
        service.deleteUser(email);
    }

    @PostMapping(path = "/signup")
    public void addUser(@RequestBody UserEntity user)
    {
        service.addUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request)
    {
        String token = service.login(request);
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,token).build();
    }
}
