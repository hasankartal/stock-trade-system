package com.payday.stocktradesystem.api.stock;

import com.google.common.collect.Lists;
import com.payday.stocktradesystem.model.stock.Stock;
import com.payday.stocktradesystem.model.stock.StockList;
import com.payday.stocktradesystem.model.stock.StockPrice;
import com.payday.stocktradesystem.model.stock.StockResponseDto;
import com.payday.stocktradesystem.service.stock.StockManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class StockApi {

    @Autowired
    StockManagementService stockManagementService;

    @GetMapping("/stock")
    public List<StockResponseDto> stock()
    {
        List<StockResponseDto> stocks = new ArrayList<>();
        StockList stockListApi = stockManagementService.findShareByCountry("Turkey");
        List<List<Stock>> stockLists =  Lists.partition(stockListApi.getData(), 20);
        List<Stock> firstStockList = stockLists.get(0);

        for (Stock stock: firstStockList) {
            StockResponseDto stockResponseDto = new StockResponseDto();
            StockPrice price = stockManagementService.stockPrice(stock.getSymbol());
            stockResponseDto.setPrice(price.getPrice());
            stockResponseDto.setSymbol(stock.getSymbol());

            stocks.add(stockResponseDto);
        }
        return stocks;
    }

    @GetMapping("/stocks")
    public CompletableFuture<List<Stock>>  getStocks()
    {
        List<StockResponseDto> stocks = new ArrayList<>();
        StockList stockListApi = stockManagementService.findShareByCountry("Turkey");
        List<List<Stock>> stockLists =  Lists.partition(stockListApi.getData(), 10);
        List<Stock> firstStockList = stockLists.get(0);

        for (Stock stock: firstStockList) {
       //     StockResponseDto stockResponseDto = stockTwelveService.getPricesByStock(stock);
         //   stockResponseDto.setSymbol(stock.getSymbol());

           // stocks.add(stockResponseDto);
        }
        return null;
    }

}
