package com.jambolao.bgfinancas;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.jambolao.bgfinancas.model.user.User;
import com.jambolao.bgfinancas.model.user.UserRepository;
import com.jambolao.bgfinancas.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user1 = new User((long) 8, "John", "Doe", "john@teste.com", "123teste", null);
        user2 = new User((long) 9, "Jane", "Doe", "jane@teste.com", "teste321", null);
    }

    @Test
    void shouldCreateUser() {
        when(userRepository.save(any(User.class))).thenReturn(user1);

        User createdUser = userService.createUser(user1);

        assertNotNull(createdUser);
        assertEquals(user1.getId(), createdUser.getId());
        verify(userRepository, times(1)).save(user1);
    }

    @Test
    void shouldReturnListOfUsers() {
        List<User> users = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.listUsers();

        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void shouldUpdateUser() {
        when(userRepository.save(any(User.class))).thenReturn(user1);

        User updatedUser = userService.updateUser(user1.getId(), user1);

        assertEquals(user1.getNome(), updatedUser.getNome());
        verify(userRepository, times(1)).save(user1);
    }

    @Test
    void shouldDeleteUserWhenExists() {
        when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));

        boolean isDeleted = userService.deleteUser(user1.getId());

        assertTrue(isDeleted);
        verify(userRepository, times(1)).deleteById(user1.getId());
    }

    @Test
    void shouldNotDeleteUserWhenNotExists() {
        when(userRepository.findById(user1.getId())).thenReturn(Optional.empty());

        boolean isDeleted = userService.deleteUser(user1.getId());

        assertFalse(isDeleted);
        verify(userRepository, never()).deleteById(anyLong());
    }

    @Test
    void shouldReadUserWhenExists() {
        when(userRepository.existsById(user1.getId())).thenReturn(true);
        when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));

        User result = userService.readUser(user1.getId());

        assertNotNull(result);
        assertEquals(user1.getId(), result.getId());
        verify(userRepository, times(1)).existsById(user1.getId());
        verify(userRepository, times(1)).findById(user1.getId());
    }

    @Test
    void shouldReturnNullWhenUserNotExists() {
        when(userRepository.existsById(user1.getId())).thenReturn(false);

        User result = userService.readUser(user1.getId());

        assertNull(result);
        verify(userRepository, times(1)).existsById(user1.getId());
    }
}
