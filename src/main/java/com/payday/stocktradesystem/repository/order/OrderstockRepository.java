package com.payday.stocktradesystem.repository.order;

import com.payday.stocktradesystem.domain.orderstock.Orderstock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrderstockRepository extends JpaRepository<Orderstock,Long> {
    List<Orderstock> findByStackSymbolEquals(String symbol);
    List<Orderstock> findByStackSymbolEqualsAndOrderTypeEqualsAndCashIsLessThan(String symbol, String orderType, BigDecimal cash);
    List<Orderstock> findByStackSymbolEqualsAndOrderTypeEqualsAndCashIsGreaterThan(String symbol, String orderType, BigDecimal cash);
}
