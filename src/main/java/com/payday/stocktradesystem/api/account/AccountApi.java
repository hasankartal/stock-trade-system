package com.payday.stocktradesystem.api.account;

import com.payday.stocktradesystem.domain.user.User;
import com.payday.stocktradesystem.exception.DataIntegrityViolationDbException;
import com.payday.stocktradesystem.model.account.AccountDto;
import com.payday.stocktradesystem.service.account.impl.AccountServiceImpl;
import com.payday.stocktradesystem.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(value="All details about the account api.")
public class AccountApi {

    @Autowired
    AccountServiceImpl accountServiceImpl;

    @Autowired
    UserService userService;

    @PostMapping("/loadCash")
    @ApiOperation(value = "Load cash into account")
    public ResponseEntity<Object> loadCash(@Valid @RequestBody AccountDto accountDto) {
        User existingUser = userService.findByUserId(accountDto.getUserId());
        if (existingUser == null || Boolean.FALSE.equals(existingUser.isEnabled() )) {
            throw new DataIntegrityViolationDbException("Could not find active user!");
        }

        try{
            accountServiceImpl.loadCash(accountDto, existingUser);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationDbException("Could not load money to account");
        }

        return ResponseEntity.ok().build();
    }
}
