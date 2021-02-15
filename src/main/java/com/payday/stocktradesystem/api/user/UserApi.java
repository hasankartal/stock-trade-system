package com.payday.stocktradesystem.api.user;

import com.payday.stocktradesystem.domain.confirmationtoken.ConfirmationToken;
import com.payday.stocktradesystem.domain.user.User;
import com.payday.stocktradesystem.exception.DataIntegrityViolationDbException;
import com.payday.stocktradesystem.model.user.UserDto;
import com.payday.stocktradesystem.model.user.UserSignInDto;
import com.payday.stocktradesystem.service.confirmationToken.impl.ConfirmationTokenServiceImpl;
import com.payday.stocktradesystem.service.email.EmailSenderService;
import com.payday.stocktradesystem.service.user.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(value="All details about the user api.")
public class UserApi {

    @Autowired
    private UserServiceImpl service;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private ConfirmationTokenServiceImpl confirmationTokenServiceImpl;

    @Value( "${spring.mail.username}" )
    private String username;

    @PostMapping("/register")
    @ApiOperation(value="Potential enterprise customer register service.")
    public boolean register(@Valid @RequestBody UserDto userDto) {
        String register;

        User existingUser = service.findByEmailIdIgnoreCase(userDto.getEmail());
        if (existingUser != null) {
            throw new DataIntegrityViolationDbException("This email already exists!");
        } else {
            register = service.register(userDto);

            emailSenderService.sendEmail(userDto.getEmail(), "Complete Registration!", username,"To confirm your account, please click here : "
                    +"http://localhost:8082/confirm-account?token="+register);
            return true;
        }
    }

    @GetMapping("/confirm-account")
    @ApiOperation(value="Customer who received link can be active its account. Customer activation service.")
    public String confirmUserAccount(@RequestParam(required = true) String token)
    {
        ConfirmationToken confirmationToken = confirmationTokenServiceImpl.findByConfirmationToken(token);

        if(token != null) {
            User user = service.findByEmailIdIgnoreCase(confirmationToken.getUser().getEmail());
            user.setEnabled(true);
            service.updateUser(user);
            return "Successful. You can sign in to platform.";
        }
        return "Unsuccessful";
    }

    @PostMapping("/sign-in")
    @ApiOperation(value="User can be sign into system.")
    public Long signIn(@Valid @RequestBody UserSignInDto userSignInDto)
    {
        User existingUser = service.findByEmailIdAndPassword(userSignInDto.getEmail(), userSignInDto.getPassword());
        if (existingUser != null && Boolean.TRUE.equals(existingUser.isEnabled())) {
            return existingUser.getUserId();
        }
        long zero = 0;
        return zero;
    }
}
