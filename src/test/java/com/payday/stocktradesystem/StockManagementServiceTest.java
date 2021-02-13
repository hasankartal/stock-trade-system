package com.payday.stocktradesystem;

import com.payday.stocktradesystem.model.stock.Stock;
import com.payday.stocktradesystem.model.stock.StockList;
import com.payday.stocktradesystem.model.stock.StockPrice;
import com.payday.stocktradesystem.model.stock.StockResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class StockManagementServiceTest {
    public static final String EXPECTED_PRICE = "1";
    public static final String EXPECTED_COUNTRY = "Turkey";
    public static final String EXPECTED_SYMBOL = "ACSEL";
    public static final String EXPECTED_CURRENCY = "TL";
    public static final String EXPECTED_NAME = "ACSEL Seluloz";
    public static final String EXPECTED_EXCHANGE = "ACSEL";
    public static final String EXPECTED_TYPE = "BIST 100";
    public static final String EXPECTED_OK = "ok";
    public static final List<Stock> EXPECTED_LIST_STOCK = new ArrayList<>();

    private Stock stock;
    private StockPrice stockPrice;
    private StockResponseDto stockResponseDto;
    private StockList stockList;
    private List<Stock> stocks;

    @Test
    public void setStock() {
        stock = new Stock();
        stock.setPrice("1");
        stock.setCountry("Turkey");
        stock.setSymbol("ACSEL");
        stock.setCurrency("TL");
        stock.setName("ACSEL Seluloz");
        stock.setExchange("ACSEL");
        stock.setType("BIST 100");

        Assertions.assertEquals(EXPECTED_PRICE, stock.getPrice());
        Assertions.assertEquals(EXPECTED_COUNTRY, stock.getCountry());
        Assertions.assertEquals(EXPECTED_SYMBOL, stock.getSymbol());
        Assertions.assertEquals(EXPECTED_CURRENCY, stock.getCurrency());
        Assertions.assertEquals(EXPECTED_NAME, stock.getName());
        Assertions.assertEquals(EXPECTED_EXCHANGE, stock.getExchange());
        Assertions.assertEquals(EXPECTED_TYPE, stock.getType());
    }

    @Test
    public void setStockList() {
        stockList = new StockList();
        stocks = new ArrayList<>();
        stockList.setData(stocks);
        stockList.setStatus("ok");

        Assertions.assertEquals(EXPECTED_OK, stockList.getStatus());
        Assertions.assertEquals(EXPECTED_LIST_STOCK, stockList.getData());
    }

    @Test
    public void setStockPrice() {
        stockPrice = new StockPrice();
        stockPrice.setPrice("1");

        Assertions.assertEquals(EXPECTED_PRICE, stockPrice.getPrice());
    }

    @Test
    public void setStockResponseDto() {
        stockResponseDto = new StockResponseDto();
        stockResponseDto.setPrice("1");
        stockResponseDto.setSymbol("ACSEL");

        Assertions.assertEquals(EXPECTED_PRICE, stockResponseDto.getPrice());
        Assertions.assertEquals(EXPECTED_SYMBOL, stockResponseDto.getSymbol());
    }


}
