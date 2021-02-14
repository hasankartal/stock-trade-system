package com.payday.stocktradesystem.service.stock;

import com.payday.stocktradesystem.model.stock.StockList;
import com.payday.stocktradesystem.model.stock.StockPrice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class StockManagementServiceTest {
    private static final String EXPECTED_COUNTRY = "Turkey";
    private static final String EXPECTED_SYMBOL = "ACSEL";

    @InjectMocks
    StockManagementService stockManagementService;

    @Mock
    private RestTemplate restTemplate;

    @Test
    void findShareByCountry() {
        StockList stockList = new StockList();
        Mockito
                .when(restTemplate.getForEntity("https://api.twelvedata.com/stocks?country="+EXPECTED_COUNTRY, StockList.class))
                .thenReturn(new ResponseEntity(stockList, HttpStatus.OK));
        stockManagementService.findShareByCountry(EXPECTED_COUNTRY);
    }

    @Test
    void stockPrice() {
        StockPrice stockPrice = new StockPrice();
        String priceUrl = "https://api.twelvedata.com/price?symbol=" + EXPECTED_SYMBOL + "&apikey=78c56e9dd5234edc8cb31e287ac9dd46&source=docs";
        Mockito
                .when(restTemplate.getForEntity(priceUrl, StockPrice.class))
                .thenReturn(new ResponseEntity(stockPrice, HttpStatus.OK));
        stockManagementService.stockPrice(EXPECTED_SYMBOL);
    }

    @Test
    void getPricesBySymbolEvent() {
    }

    @Test
    void buyShare() {
    }

    @Test
    void sellShare() {
    }


/*
    @Test
    public void setStockPriceService() {
        StockPrice stockPrice = new StockPrice();
        stockPrice.setPrice(EXPECTED_PRICE);

        Mockito.when(stockManagementService.stockPrice(EXPECTED_SYMBOL)).thenReturn(stockPrice);
        StockPrice stockPriceTest = stockManagementService.stockPrice(EXPECTED_SYMBOL);

        Assertions.assertNotEquals(stockPriceTest.getPrice(), stockPrice.getPrice());
    }
*/
}