package com.payday.stocktradesystem;

import com.payday.stocktradesystem.domain.user.User;
import com.payday.stocktradesystem.model.user.UserDto;
import com.payday.stocktradesystem.model.user.UserSignInDto;
import com.payday.stocktradesystem.repository.user.UserRepository;
import com.payday.stocktradesystem.service.user.impl.UserServiceImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    public static final String EXPECTED_USER_NAME = "Hasan";
    public static final String EXPECTED_EMAIL = "hasan.kartal@obss.com.tr";
    public static final String EXPECTED_PASSWORD = "mugiwara";

    private UserDto userDto;
    private UserSignInDto userSignInDto;

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

    @Test
    public void userDto() {
        userDto = new UserDto();
        userDto.setUserName("Hasan");
        userDto.setPassword("mugiwara");
        userDto.setEmail("hasan.kartal@obss.com.tr");

        Assertions.assertEquals(EXPECTED_USER_NAME, userDto.getUserName());
        Assertions.assertEquals(EXPECTED_PASSWORD, userDto.getPassword());
        Assertions.assertEquals(EXPECTED_EMAIL, userDto.getEmail());
    }

    @Test
    public void setUserSignInDto() {
        userSignInDto = new UserSignInDto();
        userSignInDto.setPassword("mugiwara");
        userSignInDto.setEmail("hasan.kartal@obss.com.tr");

        Assertions.assertEquals(EXPECTED_PASSWORD, userSignInDto.getPassword());
        Assertions.assertEquals(EXPECTED_EMAIL, userSignInDto.getEmail());
    }
}
