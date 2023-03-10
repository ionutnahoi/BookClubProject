package com.endava.tmd.bookclubproject.controller;

import com.endava.tmd.bookclubproject.entity.User;
import com.endava.tmd.bookclubproject.jwt.JWTUtility;
import com.endava.tmd.bookclubproject.jwt.JwtRequest;
import com.endava.tmd.bookclubproject.jwt.JwtResponse;
import com.endava.tmd.bookclubproject.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.endava.tmd.bookclubproject.service.UserService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
@AllArgsConstructor
@CrossOrigin
public class UserController {
    private final UserService userService;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final JWTUtility jwtUtility;
    @Autowired
    private final AuthenticationManager authenticationManager;

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAll());
    }

    @GetMapping(value = "getById")
    public Object getbyid(@RequestParam("idUser") Long id) {
        return userService.getbyid(id).isPresent() ? userService.getbyid(id).get() :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "login")
    public Object login(@RequestParam("username") String username, @RequestParam("password") String password) {
        return userService.login(username, password);
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
                = userService.loadUserByUsername(jwtRequest.getUsername());

        final String token =
                jwtUtility.generateToken(userDetails);

        return new JwtResponse(token);
    }

    @PostMapping(value = "register")
    public void addUser(@RequestBody final User user) {
        userService.addUser(user);
//        User user1 = userRepository.save(user);
//        URI userURI = URI.create("/register" + user1.getId());
//        return ResponseEntity.created(userURI).body(user1);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @PutMapping
    public void update(@RequestParam User user) {
        userService.update(user);
    }

    @RequestMapping(value = "/NameOrEmail", method = RequestMethod.GET)
    public User getUserByNameOrEmail(@RequestParam(value = "name") Optional<String> name, @RequestParam(value = "email") Optional<String> email) {
        return userService.getUserByNameOrEmail(name, email);
    }

//    @PostMapping(value = "/waiting")
//    public void addWaiting(@RequestParam(value = "idUserWhoBorrow") long idUser, @RequestParam(value = "title") String title) {
//        userService.addWaitingList(idUser, title);
//    }

}
