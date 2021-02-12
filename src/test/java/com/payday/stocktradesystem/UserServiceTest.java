package com.payday.stocktradesystem;

import com.payday.stocktradesystem.domain.user.User;
import com.payday.stocktradesystem.model.user.UserDto;
import com.payday.stocktradesystem.repository.user.UserRepository;
import com.payday.stocktradesystem.service.user.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void findByEmailIdIgnoreCase() {
        User user = new User();
        user.setEmail("hasankartal18@gmail.com");
        user.setPassword("mugiwara");

        String email = "hasankartal18@gmail.com";
        Mockito.when(userService.findByEmailIdIgnoreCase(email)).thenReturn(user);
        User userTest = userService.findByEmailIdIgnoreCase(user.getEmail());

        Assertions.assertEquals(userTest.getPassword(), user.getPassword());
    }

    @Test
    public void findByUserId() {
        User user = new User();
        user.setEmail("hasankartal18@gmail.com");
        user.setPassword("mugiwara");
        user.setEnabled(true);

        Mockito.when(userService.findByUserId(1)).thenReturn(user);
        User userTest = userService.findByUserId(1);

        Assertions.assertEquals(userTest, user);
    }

    @Test
    public void findByEmailIdAndPassword() {
        User user = new User();
        user.setEmail("hasankartal18@gmail.com");
        user.setPassword("mugiwara");

        Mockito.when(userService.findByEmailIdAndPassword(user.getEmail(), user.getPassword())).thenReturn(user);
        User userTest = userService.findByEmailIdAndPassword(user.getEmail(), user.getPassword());

        Assertions.assertEquals(userTest, user);
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setUserId(1);
        user.setEmail("hasankartal18@gmail.com");
        user.setPassword("mugiwara");

        User updatedUser = new User();
        updatedUser.setUserId(1);
        updatedUser.setEmail("hasankartal18@gmail.com");
        updatedUser.setPassword("mugiwara");
        Mockito.when(userService.updateUser(updatedUser)).thenReturn(updatedUser);
        User userTest = userService.updateUser(updatedUser);

        Assertions.assertEquals(userTest, user);
    }
/*
    @Test
    public void register() {

        UserDto userDto = new UserDto();
        userDto.setEmail("hasankartal18@gmail.com");
        userDto.setPassword("mugiwara");
        userDto.setUserName("Hasan Kartal");

        String token = "fce9e2d7-be5e-4b90-89b8-5c2039e54b56";
        Mockito.when(userService.register(userDto)).thenReturn(token);
        String tokenTest = userService.register(userDto);

        Assertions.assertEquals(tokenTest, token);
    }
*/
}
