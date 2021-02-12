package com.payday.stocktradesystem.model.orderstock;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@ApiModel(description="All details about the orderstockDto.")
public class OrderstockDto {

    @ApiModelProperty(notes="Order money target")
    private BigDecimal cash;

    @ApiModelProperty(notes="Share symbol")
    private String stockSymbol;

    @ApiModelProperty(notes="Share that you buy amount")
    private long stockLot;

    @ApiModelProperty(notes="There are two order types.One of them is BUY and another one is SELL.")
    private String orderType;

    @NotNull
    @ApiModelProperty(notes="User id is unique customer property.")
    private long userId;
}