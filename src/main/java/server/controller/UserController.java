package server.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import server.exception.ResourceConflictException;
import server.model.User;
import server.model.UserRequest;
import server.service.UserService;


@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(method = GET, value = "/user/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return this.userService.findById(userId);
    }

    @RequestMapping(method = GET, value = "/user/all")
    public List<User> getAllUser() {
        return this.userService.findAll();
    }

    @RequestMapping(method = DELETE, value = "/user/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        this.userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = POST, value = "/user/add")
    public ResponseEntity<?> addUser(@RequestBody UserRequest userRequest, UriComponentsBuilder ucBuilder) {

        User existUser = this.userService.findByUsername(userRequest.getUsername());
        if (existUser != null) {
            throw new ResourceConflictException(userRequest.getId(), "Username already exists");
        }
        User user = this.userService.save(userRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{userId}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @RequestMapping(method = GET, value = "/user/whoami")
    @PreAuthorize("hasRole('USER')")
    public User currentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}