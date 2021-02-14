package com.payday.stocktradesystem.api.orderStock;

import com.payday.stocktradesystem.domain.user.User;
import com.payday.stocktradesystem.exception.DataIntegrityViolationDbException;
import com.payday.stocktradesystem.model.orderstock.OrderstockDto;
import com.payday.stocktradesystem.service.orderstock.impl.OrderstockServiceImpl;
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
@Api(value="All details about the orderstock api.")
public class OrderStockApi {

    @Autowired
    OrderstockServiceImpl orderstockServiceImpl;

    @Autowired
    UserService userService;

    @PostMapping("/orderstock")
    @ApiOperation(value="Customer place an order.")
    public ResponseEntity<Object> orderStock(@Valid @RequestBody OrderstockDto orderstockDto) {
        User existingUser = userService.findByUserId(orderstockDto.getUserId());
        if (existingUser == null || Boolean.FALSE.equals(existingUser.isEnabled() )) {
            throw new DataIntegrityViolationDbException("Could not find active user!");
        }

        try{
            orderstockServiceImpl.orderstock(orderstockDto, existingUser);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationDbException("Could not load money to account");
        }

        return ResponseEntity.ok().build();
    }
}
