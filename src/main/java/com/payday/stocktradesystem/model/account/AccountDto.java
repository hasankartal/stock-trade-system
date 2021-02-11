package com.payday.stocktradesystem.model.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@ApiModel(description="All details about the accountDto.")
public class AccountDto {

    @NotNull
    @ApiModelProperty(notes="Cash is amount which belongs to customer.")
    private BigDecimal cash;

    @NotNull
    @ApiModelProperty(notes="User id is unique customer property.")
    private long userId;
}
