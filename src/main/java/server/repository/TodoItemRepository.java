package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.model.TodoItem;
import server.model.User;

import java.util.List;


public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
    TodoItem findByName(String name);
    List<TodoItem> findByUserName(String userName);
}
