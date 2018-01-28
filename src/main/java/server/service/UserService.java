package server.service;

import server.model.User;
import server.model.UserRequest;

import java.util.List;


public interface UserService {

    User findById(Long id);

    User findByUsername(String username);

    List<User> findAll();

    void deleteUser(Long id);

    User save(UserRequest user);
}