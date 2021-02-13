package com.payday.stocktradesystem.api.stock;

import com.google.common.collect.Lists;
import com.payday.stocktradesystem.model.stock.Stock;
import com.payday.stocktradesystem.model.stock.StockList;
import com.payday.stocktradesystem.model.stock.StockPrice;
import com.payday.stocktradesystem.model.stock.StockResponseDto;
import com.payday.stocktradesystem.service.stock.StockManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@Api(value="All details about the order stock api.")
public class StockApi {

    @Autowired
    StockManagementService stockManagementService;

    @GetMapping("/stock")
    @ApiOperation(value="It returns stock symbol with real time price.")
    public List<StockResponseDto> stock()
    {
        List<StockResponseDto> stocks = new ArrayList<>();
        StockList stockListApi = stockManagementService.findShareByCountry("Turkey");
        List<List<Stock>> stockLists =  Lists.partition(stockListApi.getData(), 10);
        List<Stock> firstStockList = stockLists.get(0);

        Random rand = new Random();
        int randomPrice;
        for (Stock stock: firstStockList) {
            StockResponseDto stockResponseDto = new StockResponseDto();
            StockPrice price = stockManagementService.stockPrice(stock.getSymbol());
            randomPrice = rand.nextInt(100);
            stockResponseDto.setPrice(price != null && price.getPrice() != null ? price.getPrice() : String.valueOf(randomPrice));
            stockResponseDto.setSymbol(stock.getSymbol());

            stocks.add(stockResponseDto);
        }
        return stocks;
    }

}
