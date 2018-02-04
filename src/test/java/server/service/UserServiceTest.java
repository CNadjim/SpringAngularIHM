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

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {


    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;


    @Test
    public void add_should_add_and_return_CREATED(){
        //given
        User user = new User();
        when(userRepository.exists(eq(user.getId()))).thenReturn(false);

        //when
        ResponseEntity<Void> responseEntity = userService.addUser(user);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        verify(userRepository).exists(eq(user.getId()));
        verify(userRepository).save(eq(user));
    }

    @Test
    public void add_should_not_add_and_return_CONFLICT(){
        //given
        User user = new User();
        when(userRepository.exists(eq(user.getId()))).thenReturn(true);

        //when
        ResponseEntity<Void> responseEntity = userService.addUser(user);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        verify(userRepository).exists(eq(user.getId()));
    }
}
