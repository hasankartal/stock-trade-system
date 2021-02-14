package com.payday.stocktradesystem.service.user.impl;

import com.payday.stocktradesystem.domain.user.User;
import com.payday.stocktradesystem.repository.confirmationtoken.ConfirmationTokenRepository;
import com.payday.stocktradesystem.repository.user.UserRepository;
import com.payday.stocktradesystem.service.confirmationToken.impl.ConfirmationTokenServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    private static final String EXPECTED_EMAIL = "hasan.kartal@obss.com.tr";
    private static final String EXPECTED_PASSWORD = "mugiwara";
    private static final long EXPECTED_USER_ID = 1;

    @InjectMocks
    UserServiceImpl userService;

    @InjectMocks
    ConfirmationTokenServiceImpl confirmationTokenServiceImpl;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Test
    void findByEmailIdIgnoreCase() {
        User user = new User();
        user.setEmail(EXPECTED_EMAIL);
        user.setPassword(EXPECTED_PASSWORD);

        Mockito.when(userService.findByEmailIdIgnoreCase(EXPECTED_EMAIL)).thenReturn(user);
        User userTest = userService.findByEmailIdIgnoreCase(user.getEmail());

        Assertions.assertEquals(userTest.getPassword(), user.getPassword());
    }

    @Test
    void findByUserId() {
        User user = new User();
        user.setEmail(EXPECTED_EMAIL);
        user.setPassword(EXPECTED_PASSWORD);
        user.setEnabled(true);

        Mockito.when(userService.findByUserId(1)).thenReturn(user);
        User userTest = userService.findByUserId(1);

        Assertions.assertEquals(userTest, user);
    }

    @Test
    void findByEmailIdAndPassword() {
        User user = new User();
        user.setEmail(EXPECTED_EMAIL);
        user.setPassword(EXPECTED_PASSWORD);

        Mockito.when(userService.findByEmailIdAndPassword(user.getEmail(), user.getPassword())).thenReturn(user);
        User userTest = userService.findByEmailIdAndPassword(user.getEmail(), user.getPassword());

        Assertions.assertEquals(userTest, user);
    }

    @Test
    void updateUser() {
        User user = new User();
        user.setUserId(EXPECTED_USER_ID);
        user.setEmail(EXPECTED_EMAIL);
        user.setPassword(EXPECTED_PASSWORD);

        User updatedUser = new User();
        updatedUser.setUserId(EXPECTED_USER_ID);
        updatedUser.setEmail(EXPECTED_EMAIL);
        updatedUser.setPassword(EXPECTED_PASSWORD);
        Mockito.when(userService.updateUser(updatedUser)).thenReturn(updatedUser);
        User userTest = userService.updateUser(updatedUser);

        Assertions.assertEquals(userTest, user);
    }

    @Test
    void register() {
        /*
         confirmationTokenService = new ConfirmationTokenService(confirmationTokenRepository);
        userService = new UserServiceImpl(userRepository, confirmationTokenService);

        userDto = new UserDto();
        userDto.setEmail("hasankartal18@gmail.com");
        userDto.setPassword("mugiwara");
        userDto.setUserName("Hasan Kartal");

        user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setUserName(user.getUserName());
        user.setEnabled(false);

        String token = UUID.randomUUID().toString();
        Mockito.when(userService.register(userDto)).thenReturn(token);
        String tokenTest = userService.register(userDto);

        Assertions.assertNotEquals(tokenTest, token);
         */
    }
}