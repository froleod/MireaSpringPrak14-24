package com.ldf.springprak14;

import com.ldf.springprak14.Entity.User;
import com.ldf.springprak14.Repo.UserRepository;
import com.ldf.springprak14.Service.AppService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AppServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AppService appService;


    @Test
    public void testAddUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password123");

        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");

        appService.addUser(user);

        verify(userRepository, times(1)).save(user);

        assertEquals("encodedPassword", user.getPassword());
    }
}
