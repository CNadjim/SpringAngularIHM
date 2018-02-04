package server.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import server.model.TodoItem;
import server.model.User;
import server.repository.TodoItemRepository;
import server.repository.UserRepository;
import server.service.TodoItemService;
import server.service.UserService;

import javax.inject.Inject;
import java.util.List;

@RestController
public class UserController {

    @Inject
    private UserService userService;

    @RequestMapping(path = "/api/user/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllUser() {
        return this.userService.getAllUser();
    }

    @RequestMapping(path = "/api/user/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return this.userService.deleteUser(userId);
    }

    @RequestMapping(path = "/api/user/add", method = RequestMethod.POST)
    public ResponseEntity<?> deleteUser(@RequestBody User user) {
        return this.userService.addUser(user);
    }

    @RequestMapping(path = "/api/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return this.userService.getUser(userId);
    }



}
