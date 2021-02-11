package com.payday.stocktradesystem.service.user;

import com.payday.stocktradesystem.domain.user.User;
import com.payday.stocktradesystem.model.user.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    User findByEmailIdIgnoreCase(String email);
    User findByUserId(long userId);
    User findByEmailIdAndPassword(String email, String password);
    User updateUser(User user);
    ResponseEntity<Object> register(UserDto userDto);
}
