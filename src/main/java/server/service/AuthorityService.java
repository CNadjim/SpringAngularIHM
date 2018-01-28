package server.service;
import server.model.Authority;

import java.util.List;

public interface AuthorityService {
    List<Authority> findById(Long id);

    List<Authority> findByname(String name);

}