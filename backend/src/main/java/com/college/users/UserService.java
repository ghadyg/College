package com.college.users;

import com.college.authentication.JWTUtils;
import com.college.exception.AlreadyExistException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserDTOMapper mapper;
    private final PasswordEncoder encoder;

    private final AuthenticationManager authenticationManager;

    private final JWTUtils utils;

    public UserService(UserRepository repository, UserDTOMapper mapper, PasswordEncoder encoder, AuthenticationManager authenticationManager, JWTUtils utils) {
        this.repository = repository;
        this.mapper = mapper;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
        this.utils = utils;
    }

    public List<UserDTO> getAllUsers()
    {
            return repository.findAll().stream().map(mapper).collect(Collectors.toList());
    }

    public UserDTO getUserById(String email)
    {
        return repository.findById(email).map(mapper).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public void addUser(UserEntity user)
    {
        if(repository.existsByEmail(user.getEmail()))
            throw new AlreadyExistException("User already exists");
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
    }

    public void deleteUser (String email)
    {
        if(repository.existsByEmail(email))
            throw new UsernameNotFoundException("User not found");
        repository.deleteById(email);
    }

    public String login(AuthenticationRequest request)
    {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.email(),request.password())
        );
        UserEntity user = (UserEntity)authentication.getPrincipal();
        UserDTO userDTO = mapper.apply(user);

        return utils.issueToken(userDTO.email(), Map.of("scopes",userDTO.role().name()));
    }




}
