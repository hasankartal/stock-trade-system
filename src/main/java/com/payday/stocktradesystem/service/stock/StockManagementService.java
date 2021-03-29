package com.payday.stocktradesystem.service.stock;

import com.payday.stocktradesystem.domain.orderstock.Orderstock;
import com.payday.stocktradesystem.domain.user.User;
import com.payday.stocktradesystem.exception.DataIntegrityViolationDbException;
import com.payday.stocktradesystem.model.account.AccountDto;
import com.payday.stocktradesystem.model.stock.Stock;
import com.payday.stocktradesystem.model.stock.StockList;
import com.payday.stocktradesystem.model.stock.StockPrice;
import com.payday.stocktradesystem.service.account.impl.AccountServiceImpl;
import com.payday.stocktradesystem.service.email.EmailSenderService;
import com.payday.stocktradesystem.service.orderstock.impl.OrderstockServiceImpl;
import com.payday.stocktradesystem.service.user.UserService;
import com.payday.stocktradesystem.service.user.impl.UserServiceImpl;
import com.payday.stocktradesystem.util.Utils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class StockManagementService {

    @Autowired
    OrderstockServiceImpl orderstockServiceImpl;

    @Autowired
    AccountServiceImpl accountServiceImpl;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment env;

    @Cacheable(value="cacheShareByCountry")
    public StockList findShareByCountry(String country) {
        String url = "https://api.twelvedata.com/stocks?country=" + country;

        ResponseEntity<StockList> result = restTemplate.getForEntity(url, StockList.class);
        StockList stockList = result.getBody();

        return stockList;
    }

    public StockPrice stockPrice(String symbol) {
        String priceUrl = "https://api.twelvedata.com/price?symbol=" + symbol + "&apikey=78c56e9dd5234edc8cb31e287ac9dd46&source=docs";
        ResponseEntity<StockPrice> priceResult = restTemplate.getForEntity(priceUrl, StockPrice.class);
        if (priceResult !=null) {
            StockPrice stockPrice = priceResult.getBody();
            return stockPrice;
        }
        return null;
    }

    @Async("stockOrder")
    public void getPricesBySymbolEvent(Stock stock) {
        StockPrice price = stockPrice(stock.getSymbol());
        Random rand = new Random();
        double randomPrice = rand.nextDouble() * 100;
        BigDecimal priceDecimal = new BigDecimal(price != null && price.getPrice() != null ? price.getPrice() : String.valueOf(randomPrice));

        buyShare(priceDecimal, stock);
        sellShare(priceDecimal, stock);
    }

    /*
     * E.g. buy 100 TSLA shares with target price 200$. The order should be filled  when the price is equal or under 200$
     */
    public void buyShare(BigDecimal priceDecimal, Stock stock) {
        List<Orderstock> orderstockBuyList = orderstockServiceImpl.buyStock(stock.getSymbol(), Utils.BUY, priceDecimal);
        for (Orderstock orderstock : orderstockBuyList) {
            AccountDto accountDto = new AccountDto();
            BigDecimal stockLot = new BigDecimal(orderstock.getStockLot());
            BigDecimal cash = stockLot.multiply(priceDecimal);
            accountDto.setCash(cash);

            User existingUser = userService.findByUserId(orderstock.getUser().getUserId());
            if (existingUser == null || Boolean.FALSE.equals(existingUser.isEnabled() )) {
                throw new DataIntegrityViolationDbException("Could not find active user!");
            }

            accountServiceImpl.withdrawCash(accountDto, existingUser);
            orderstock.setActive(false);
            orderstockServiceImpl.updateOrderstock(orderstock);

            String mail = env.getProperty("spring.mail.username");
            emailSenderService.sendEmail(existingUser.getEmail(), "STOCK BUY NOTIFICATION",
                    mail,"Stock Symbol -> " + stock.getSymbol() + " - "
                            + " Stock Price -> " + priceDecimal);
        }
    }

    /*
     * Sell share for 200$, the  order will be filled when the TSLA price is equal or  more than 200$.
     */
    public void sellShare(BigDecimal priceDecimal, Stock stock) {
        List<Orderstock> orderstockSellList = orderstockServiceImpl.sellStock(stock.getSymbol(), Utils.SELL, priceDecimal);
        for (Orderstock orderstock : orderstockSellList) {
            AccountDto accountDto = new AccountDto();
            BigDecimal stockLot = new BigDecimal(orderstock.getStockLot());
            BigDecimal cash = stockLot.multiply(priceDecimal);
            accountDto.setCash(cash);

            User existingUser = userService.findByUserId(orderstock.getUser().getUserId());
            if (existingUser == null || Boolean.FALSE.equals(existingUser.isEnabled() )) {
                throw new DataIntegrityViolationDbException("Could not find active user!");
            }

            accountServiceImpl.loadCash(accountDto, existingUser);
            orderstock.setActive(false);
            orderstockServiceImpl.updateOrderstock(orderstock);

            String mail = env.getProperty("spring.mail.username");
            emailSenderService.sendEmail(existingUser.getEmail(), "STOCK SELL NOTIFICATION",
                    mail,"Stock Symbol -> " + stock.getSymbol() + " - "
                            + " Stock Price -> " + priceDecimal);
        }
    }

    /* In order to performance, prices should be get asynchronously.
    @Async("taskExecutor")
    public CompletableFuture<List<Stock>> getPricesBySymbol(List<Stock> stockList) {
        List<Stock> stockList1 = new ArrayList<Stock>();
        for (Stock stock: stockList) {
            Stock stock1 = new Stock();
            stock1.setSymbol(stock.getSymbol());
            StockPrice price = stockPrice(stock.getSymbol());
            stock1.setCountry(price.getPrice());

            stockList1.add(stock1);
        }
        return CompletableFuture.completedFuture(stockList1);
    }
    */
}
