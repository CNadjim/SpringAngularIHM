package server.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import server.model.TodoItem;
import server.repository.TodoItemRepository;

import java.util.List;

@RestController
public class TodoItemController {

    @Autowired
    private TodoItemRepository todoItemRepository;

    @RequestMapping(path = "/api/todoitem", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<TodoItem> getAllTodoItem() {
        return todoItemRepository.findAll();
    }

    @RequestMapping(path = "/api/todoitem/{userName}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<TodoItem> getAllTodoItemByUserId(@PathVariable String userName) {
        return todoItemRepository.findByUserName(userName);
    }

    @RequestMapping(path = "/api/todoitem/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> deleteTodoItem(@PathVariable Long id) {
        TodoItem todoItem = todoItemRepository.findOne(id);
        if (todoItem != null){
            todoItemRepository.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
