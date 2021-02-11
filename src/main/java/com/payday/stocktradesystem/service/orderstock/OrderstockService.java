package com.payday.stocktradesystem.service.orderstock;

import com.payday.stocktradesystem.domain.orderstock.Orderstock;
import com.payday.stocktradesystem.repository.order.OrderstockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderstockService {

    @Autowired
    OrderstockRepository orderstockRepository;

    public List<Orderstock> buyStock(String stackSymbol, String orderType, BigDecimal cash) {
        return orderstockRepository.findByStackSymbolEqualsAndOrderTypeEqualsAndCashIsGreaterThan(stackSymbol, orderType, cash);
    }

    public List<Orderstock> sellStock(String stackSymbol, String orderType, BigDecimal cash) {
        return orderstockRepository.findByStackSymbolEqualsAndOrderTypeEqualsAndCashIsLessThan(stackSymbol, orderType, cash);
    }
    public List<Orderstock> findByStackSymbolEquals(String symbol) {
        return orderstockRepository.findByStackSymbolEquals(symbol);
    }

    public Orderstock updateOrderstock(Orderstock orderstock) {
        return orderstockRepository.save(orderstock);
    }
}
