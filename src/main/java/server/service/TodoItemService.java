package server.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import server.model.TodoItem;
import server.repository.TodoItemRepository;

import java.util.List;

import static io.jsonwebtoken.lang.Collections.isEmpty;
import static java.util.Objects.isNull;

@Component
public class TodoItemService {


    private final Log logger = LogFactory.getLog(this.getClass());

    private final TodoItemRepository todoItemRepository;

    public TodoItemService(final TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    public ResponseEntity<List<TodoItem>> getAllTodoItem() {
        List<TodoItem> todoItems =  this.todoItemRepository.findAll();
        if (todoItems.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else
            return new ResponseEntity<>(todoItems,HttpStatus.OK);
    }

    public ResponseEntity<List<TodoItem>> getAllTodoItemByUserName(String userName) {
        List<TodoItem> todoItems =  this.todoItemRepository.findByUserName(userName);
        if (todoItems.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else
            return new ResponseEntity<>(todoItems,HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteTodoItem(Long id) {
        TodoItem todoItem = todoItemRepository.findOne(id);
        if (!isNull(todoItem)){
            todoItemRepository.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
