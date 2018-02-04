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
    public void get_all_should_return_list(){
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
        verify(todoItemRepository).findAll();
    }

    @Test
    public void get_should_return_no_CONTENT(){
        //given
        List<TodoItem> todoItems = new ArrayList<>();
        String userName = "";
        when(todoItemRepository.findByUserName(userName)).thenReturn(todoItems);

        //when
        ResponseEntity<List<TodoItem>> responseEntity = todoItemService.getAllTodoItemByUserName(userName);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(todoItemRepository).findByUserName(eq(userName));
    }

    @Test
    public void delete_should_delete_and_return_OK(){
        //given
        Long id = new Long(1);
        when(todoItemRepository.exists(eq(id))).thenReturn(true);

        //when
        ResponseEntity<Void> responseEntity = todoItemService.deleteTodoItem(id);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(todoItemRepository).exists(eq(id));
        verify(todoItemRepository).delete(eq(id));
    }

    @Test
    public void delete_should_not_delete_and_return_NOFOUND(){
        //given
        Long id = new Long(1);
        when(todoItemRepository.exists(eq(id))).thenReturn(false);

        //when
        ResponseEntity<Void> responseEntity = todoItemService.deleteTodoItem(id);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(todoItemRepository).exists(eq(id));
    }


    @Test
    public void add_should_add_and_return_CREATED(){
        //given
        TodoItem todoItem = new TodoItem();
        User user = new User();
        when(userRepository.findByUsername(todoItem.getUserName())).thenReturn(user);

        //when
        ResponseEntity<Void> responseEntity = todoItemService.add(todoItem);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        verify(todoItemRepository).save(eq(todoItem));
        verify(userRepository).findByUsername(todoItem.getUserName());
    }

    @Test
    public void add_should_not_add_and_return_USER_NOT_FOUND(){
        //given
        TodoItem todoItem = new TodoItem();
        when(userRepository.findByUsername(todoItem.getUserName())).thenReturn(null);

        //when
        ResponseEntity<Void> responseEntity = todoItemService.add(todoItem);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
