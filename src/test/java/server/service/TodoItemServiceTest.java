package server.service;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import server.model.TodoItem;
import server.model.User;
import server.repository.TodoItemRepository;
import server.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TodoItemServiceTest {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Mock
    private TodoItemRepository todoItemRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TodoItemService todoItemService;


    @Test
    public void get_should_return_list(){
        //given
        List<TodoItem> todoItems = new ArrayList<>();
        TodoItem todoItem = new TodoItem();
        todoItems.add(todoItem);
        when(todoItemRepository.findAll()).thenReturn(todoItems);

        //when
        ResponseEntity<List<TodoItem>> responseEntity = todoItemService.getAllTodoItem();
        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().contains(todoItem)).isEqualTo(true);
    }

    @Test
    public void get_should_return_not_found(){
        //given
        String userName = "USERNAME_IMAGINAIRE";
        when(userRepository.findByUsername(userName)).thenReturn(null);

        //when
        ResponseEntity<List<TodoItem>> responseEntity = todoItemService.getAllTodoItemByUserName(userName);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
