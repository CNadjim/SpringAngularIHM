package server.controller;

import java.util.ArrayList;
import java.util.List;

import server.model.User;
import server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    UserRepository repository;

    @GetMapping(value="/",  produces=MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        Iterable<User> Users = repository.findAll();
        Users.forEach(list::add);
        return list;
    }

    @GetMapping(value="/{id}",  produces=MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable long id){
        User User = repository.findOne(id);
        return User ;
    }

    @PostMapping(value="/add")
    public User postUser(@RequestBody User User) {
        repository.save(new User(User.getFirstName(), User.getLastName()));
        return User;
    }

    @DeleteMapping(value="/delete/{id}")
    public void deleteUser(@PathVariable long id){
        repository.delete(id);
    }



}