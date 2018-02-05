package server.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import server.model.TodoItem;
import server.service.TodoItemService;

import javax.inject.Inject;
import java.util.List;


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

    @RequestMapping(path = "/api/todoitem/add", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> addTodoItem(@RequestBody TodoItem todoItem) {
        return this.todoItemService.add(todoItem);
    }


}
