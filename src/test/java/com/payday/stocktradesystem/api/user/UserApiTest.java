package com.payday.stocktradesystem.api.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payday.stocktradesystem.domain.confirmationtoken.ConfirmationToken;
import com.payday.stocktradesystem.domain.orderstock.Orderstock;
import com.payday.stocktradesystem.domain.user.User;
import com.payday.stocktradesystem.model.orderstock.OrderstockDto;
import com.payday.stocktradesystem.model.user.UserDto;
import com.payday.stocktradesystem.model.user.UserSignInDto;
import com.payday.stocktradesystem.repository.user.UserRepository;
import com.payday.stocktradesystem.service.confirmationToken.impl.ConfirmationTokenServiceImpl;
import com.payday.stocktradesystem.service.email.EmailSenderService;
import com.payday.stocktradesystem.service.user.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserApi.class)
class UserApiTest {
    private static final String EXPECTED_EMAIL = "hasan.kartal@obss.com.tr";
    private static final String EXPECTED_PASSWORD = "mugiwara";
    private static final String EXPECTED_USER_NAME = "Hasan";
    private static final long EXPECTED_USER_ID = 0;
    private static final String EXPECTED_MESSAGE = "Successful. You can sign in to platform.";
    private static final String EXPECTED_TOKEN = "419f7735-0699-4544-9a23-f4f0ee650f01";
    private static final String EXPECTED_CONTENT_TYPE = "application/json";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserServiceImpl userService;

    @MockBean
    private EmailSenderService emailSenderService;

    @MockBean
    private ConfirmationTokenServiceImpl confirmationTokenServiceImpl;

    @MockBean
    UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void register() {
        UserDto userDto = new UserDto();
        userDto.setEmail(EXPECTED_EMAIL);
        userDto.setPassword(EXPECTED_PASSWORD);
        userDto.setUserName(EXPECTED_USER_NAME);

        try {
            this.mockMvc.perform(post("/register")
                    .contentType(EXPECTED_CONTENT_TYPE)
                    .content(objectMapper.writeValueAsString(userDto)))
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void registerNullException() {
        UserDto userDto = new UserDto();
        userDto.setEmail(EXPECTED_EMAIL);
        userDto.setPassword(EXPECTED_PASSWORD);
        userDto.setUserName(EXPECTED_USER_NAME);

        User existingUser = new User();
        existingUser.setEnabled(true);
        existingUser.setUserId(1);

        Mockito
                .when(userService.findByEmailIdIgnoreCase(EXPECTED_PASSWORD))
                .thenReturn(existingUser);
        try {
            this.mockMvc.perform(post("/register")
                    .contentType(EXPECTED_CONTENT_TYPE)
                    .content(objectMapper.writeValueAsString(userDto)))
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void confirmUserAccount() {
        String token = EXPECTED_TOKEN;
        String url = "/confirm-account?token=" + EXPECTED_TOKEN;
        try {
            User user = new User();
            user.setEmail(EXPECTED_EMAIL);

            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            Mockito
                    .when(confirmationTokenServiceImpl.findByConfirmationToken(token))
                    .thenReturn(confirmationToken);

            Mockito
                    .when(userService.findByEmailIdIgnoreCase(EXPECTED_EMAIL))
                    .thenReturn(user);

            this.mockMvc.perform(get(url)
                    .contentType(EXPECTED_CONTENT_TYPE)
                    .content(objectMapper.writeValueAsString(token)))
                .andExpect(content().string(String.valueOf(EXPECTED_MESSAGE)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void signIn() {
        UserSignInDto userSignInDto = new UserSignInDto();
        userSignInDto.setEmail(EXPECTED_EMAIL);
        userSignInDto.setPassword(EXPECTED_PASSWORD);
        try {
            this.mockMvc.perform(post("/sign-in")
                    .contentType(EXPECTED_CONTENT_TYPE)
                    .content(objectMapper.writeValueAsString(userSignInDto)))
                    .andExpect(content().string(String.valueOf(EXPECTED_USER_ID)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}