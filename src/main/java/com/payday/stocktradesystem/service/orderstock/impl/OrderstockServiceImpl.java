package com.payday.stocktradesystem.service.orderstock.impl;

import com.payday.stocktradesystem.domain.account.Account;
import com.payday.stocktradesystem.domain.orderstock.Orderstock;
import com.payday.stocktradesystem.domain.user.User;
import com.payday.stocktradesystem.exception.DataIntegrityViolationDbException;
import com.payday.stocktradesystem.model.orderstock.OrderstockDto;
import com.payday.stocktradesystem.repository.order.OrderstockRepository;
import com.payday.stocktradesystem.service.account.impl.AccountServiceImpl;
import com.payday.stocktradesystem.service.orderstock.OrderstockService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderstockServiceImpl implements OrderstockService {
    private final OrderstockRepository orderstockRepository;
    private final AccountServiceImpl accountServiceImpl;

    @Override
    public List<Orderstock> buyStock(String stockSymbol, String orderType, BigDecimal cash) {
        return orderstockRepository.findByStockSymbolEqualsAndOrderTypeEqualsAndCashIsGreatThanAbove(stockSymbol, orderType, cash);
    }

    @Override
    public List<Orderstock> sellStock(String stockSymbol, String orderType, BigDecimal cash) {
        return orderstockRepository.findByStockSymbolEqualsAndOrderTypeEqualsAndCashIsLessThanAbove(stockSymbol, orderType, cash);
    }

    @Override
    public Orderstock updateOrderstock(Orderstock orderstock) {
        return orderstockRepository.save(orderstock);
    }

    @Override
    public Orderstock orderstock(OrderstockDto orderstockDto, User user) {
        Orderstock orderstock = new Orderstock();
        orderstock.setActive(true);
        orderstock.setCash(orderstockDto.getCash());
        orderstock.setOrderType(orderstockDto.getOrderType());
        orderstock.setStockLot(orderstockDto.getStockLot());
        orderstock.setStockSymbol(orderstockDto.getStockSymbol());
        orderstock.setUser(user);

        try {
            Account account = new Account();
            account.setUser(user);

            Account existingAccount = accountServiceImpl.findByUser(user);
            BigDecimal stockLot = new BigDecimal(orderstock.getStockLot());
            BigDecimal cash = stockLot.multiply(orderstock.getCash());

            if(existingAccount.getCash().compareTo(cash) == -1) {
                throw new DataIntegrityViolationDbException("There is not enough money on your account. It is not allowed to buy stock");
            }
            return orderstockRepository.save(orderstock);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationDbException("Could not register in db");
        }
    }
}
