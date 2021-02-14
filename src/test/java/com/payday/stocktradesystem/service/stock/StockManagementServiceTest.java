package com.payday.stocktradesystem.service.stock;

import com.payday.stocktradesystem.domain.orderstock.Orderstock;
import com.payday.stocktradesystem.domain.user.User;
import com.payday.stocktradesystem.model.stock.Stock;
import com.payday.stocktradesystem.model.stock.StockList;
import com.payday.stocktradesystem.model.stock.StockPrice;
import com.payday.stocktradesystem.repository.account.AccountRepository;
import com.payday.stocktradesystem.repository.order.OrderstockRepository;
import com.payday.stocktradesystem.service.account.impl.AccountServiceImpl;
import com.payday.stocktradesystem.service.email.EmailSenderService;
import com.payday.stocktradesystem.service.orderstock.impl.OrderstockServiceImpl;
import com.payday.stocktradesystem.service.user.UserService;
import com.payday.stocktradesystem.service.user.impl.UserServiceImpl;
import com.payday.stocktradesystem.util.Utils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class StockManagementServiceTest {
    private static final String EXPECTED_COUNTRY = "Turkey";
    private static final String EXPECTED_SYMBOL = "ACSEL";
    private static final String EXPECTED_PRICE = "1";
    private static final String EXPECTED_CURRENCY = "TL";
    private static final String EXPECTED_NAME = "ACSEL Seluloz";
    private static final String EXPECTED_EXCHANGE = "ACSEL";
    private static final String EXPECTED_TYPE = "BIST 100";

    @InjectMocks
    StockManagementService stockManagementService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    StockPrice stockPrice;

    @InjectMocks
    OrderstockServiceImpl orderstockService;

    @InjectMocks
    AccountServiceImpl accountServiceImpl;

    @InjectMocks
    UserServiceImpl userService;

    @InjectMocks
    EmailSenderService emailSenderService;

    @Mock
    JavaMailSender javaMailSender;

    @Mock
    OrderstockRepository orderstockRepository;

    @Mock
    AccountRepository accountRepository;

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
        Stock stock = new Stock();
        stock.setType(EXPECTED_TYPE);
        stock.setName(EXPECTED_NAME);
        stock.setCountry(EXPECTED_COUNTRY);
        stock.setPrice(EXPECTED_PRICE);
        stock.setSymbol(EXPECTED_SYMBOL);
        stock.setCurrency(EXPECTED_CURRENCY);
        stock.setExchange(EXPECTED_EXCHANGE);

        List<Orderstock> orderstockList = new ArrayList<>();
        Orderstock orderstock = new Orderstock();
        User user = new User();
        user.setUserId(1);
        orderstock.setUser(user);
        orderstock.setActive(true);
        orderstock.setStockLot(10);
        orderstockList.add(orderstock);

        stockManagementService = new StockManagementService(orderstockService,accountServiceImpl,userService,emailSenderService,restTemplate);
/*
        Mockito
                .when(orderstockService.buyStock(stock.getSymbol(), Utils.BUY, new BigDecimal(1)))
                .thenReturn(orderstockList);
*/
        stockManagementService.getPricesBySymbolEvent(stock);
    }

    @Test
    void buyShare() {
        Stock stock = new Stock();
        stock.setType(EXPECTED_TYPE);
        stock.setName(EXPECTED_NAME);
        stock.setCountry(EXPECTED_COUNTRY);
        stock.setPrice(EXPECTED_PRICE);
        stock.setSymbol(EXPECTED_SYMBOL);
        stock.setCurrency(EXPECTED_CURRENCY);
        stock.setExchange(EXPECTED_EXCHANGE);

        stockManagementService = new StockManagementService(orderstockService,accountServiceImpl,userService,emailSenderService,restTemplate);

        stockManagementService.buyShare(new BigDecimal(10),stock);
    }

    @Test
    void sellShare() {
        Stock stock = new Stock();
        stock.setType(EXPECTED_TYPE);
        stock.setName(EXPECTED_NAME);
        stock.setCountry(EXPECTED_COUNTRY);
        stock.setPrice(EXPECTED_PRICE);
        stock.setSymbol(EXPECTED_SYMBOL);
        stock.setCurrency(EXPECTED_CURRENCY);
        stock.setExchange(EXPECTED_EXCHANGE);

        stockManagementService = new StockManagementService(orderstockService,accountServiceImpl,userService,emailSenderService,restTemplate);
        stockManagementService.sellShare(new BigDecimal(10),stock);
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