package server.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import server.model.TodoItem;
import server.service.TodoItemService;

import javax.inject.Inject;
import java.util.List;

import static java.util.Objects.isNull;


@RestController
public class TodoItemController {


    @Inject
    private TodoItemService todoItemService;

    @RequestMapping(path = "/api/todoitem", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<TodoItem>> getAllTodoItem() {
        return todoItemService.getAllTodoItem();
    }


    @RequestMapping(path = "/api/todoitem/{userName}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<TodoItem>> getAllTodoItemByUserName(@PathVariable String userName) {
        return todoItemService.getAllTodoItemByUserName(userName);
    }

    @RequestMapping(path = "/api/todoitem/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> deleteTodoItem(@PathVariable Long id) {
        return this.todoItemService.deleteTodoItem(id);
    }


}
