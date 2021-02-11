package com.payday.stocktradesystem.model.stock;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="All details about the stockResponseDto.")
public class StockResponseDto {

    @ApiModelProperty(notes="It represents stock.")
    private String symbol;

    @ApiModelProperty(notes="Real time stock price.")
    private String price;
}
