package com.payday.stocktradesystem.api.orderStock;

import com.payday.stocktradesystem.domain.orderstock.Orderstock;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(value="All details about the order stock api.")
public class OrderStockApi {

    @PostMapping("/orderStock")
    @ApiOperation(value="User place an order.")
    public void orderStock(@Valid @RequestBody Orderstock orderstock) {

    }
}
