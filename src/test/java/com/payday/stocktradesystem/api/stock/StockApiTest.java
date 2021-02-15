package com.payday.stocktradesystem.api.stock;

import com.payday.stocktradesystem.api.user.UserApi;
import com.payday.stocktradesystem.model.stock.Stock;
import com.payday.stocktradesystem.model.stock.StockList;
import com.payday.stocktradesystem.model.stock.StockResponseDto;
import com.payday.stocktradesystem.service.stock.StockManagementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = StockApi.class)
class StockApiTest {
    private static final String EXPECTED_COUNTRY = "Turkey";
    private static final String EXPECTED_SYMBOL = "ACSEL";
    private static final String EXPECTED_PRICE = "1";
    private static final String EXPECTED_CURRENCY = "TL";
    private static final String EXPECTED_NAME = "ACSEL Seluloz";
    private static final String EXPECTED_EXCHANGE = "ACSEL";
    private static final String EXPECTED_TYPE = "BIST 100";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    StockManagementService stockManagementService;

    @Test
    void stock() {
        StockList stockList = new StockList();
        Stock stock = new Stock();
        stock.setType(EXPECTED_TYPE);
        stock.setName(EXPECTED_NAME);
        stock.setCountry(EXPECTED_COUNTRY);
        stock.setPrice(EXPECTED_PRICE);
        stock.setSymbol(EXPECTED_SYMBOL);
        stock.setCurrency(EXPECTED_CURRENCY);
        stock.setExchange(EXPECTED_EXCHANGE);
        List<Stock> stocks = new ArrayList<Stock>();
        stocks.add(stock);
        stockList.setData(stocks);

        Mockito
                .when(stockManagementService.findShareByCountry(EXPECTED_COUNTRY))
                .thenReturn(stockList);
        try {
            this.mockMvc.perform(get("/stock"))
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}