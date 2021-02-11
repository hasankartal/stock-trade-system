package com.payday.stocktradesystem.api.user;

import com.payday.stocktradesystem.domain.confirmationtoken.ConfirmationToken;
import com.payday.stocktradesystem.domain.user.User;
import com.payday.stocktradesystem.exception.DataIntegrityViolationDbException;
import com.payday.stocktradesystem.model.user.UserDto;
import com.payday.stocktradesystem.model.user.UserSignInDto;
import com.payday.stocktradesystem.service.confirmationToken.ConfirmationTokenService;
import com.payday.stocktradesystem.service.user.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(value="All details about the user api.")
public class UserApi {

    @Autowired
    private UserServiceImpl service;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @PostMapping("/register")
    @ApiOperation(value="Potential enterprise customer register service.")
    public ResponseEntity<Object> register(@Valid @RequestBody UserDto userDto) {
        ResponseEntity<Object> savedUser;

        User existingUser = service.findByEmailIdIgnoreCase(userDto.getEmail());
        if (existingUser != null) {
            throw new DataIntegrityViolationDbException("This email already exists!");
        } else {
            try {
                savedUser = service.register(userDto);
                return savedUser;
            } catch (DataIntegrityViolationDbException ex) {
                throw new DataIntegrityViolationDbException("Could not create user");
            }
        }
    }

    @GetMapping("/confirm-account")
    @ApiOperation(value="Customer who received link can be active its account. Customer activation service.")
    public String confirmUserAccount(@RequestParam("token") String confirmationToken)
    {
        ConfirmationToken token = confirmationTokenService.findByConfirmationToken(confirmationToken);

        if(token != null) {
            User user = service.findByEmailIdIgnoreCase(token.getUser().getEmail());
            user.setEnabled(true);
            service.updateUser(user);
        }
        return "Successful. You can sign in to platform.";
    }

    @GetMapping("/sign-in")
    @ApiOperation(value="User can be sign into system.")
    public Boolean signIn(@Valid @RequestBody UserSignInDto userSignInDto)
    {
        User existingUser = service.findByEmailIdAndPassword(userSignInDto.getEmail(), userSignInDto.getPassword());
        if (existingUser != null && Boolean.TRUE.equals(existingUser.isEnabled())) {
            return true;
        }
        return false;
    }
}
