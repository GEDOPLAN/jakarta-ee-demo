package de.gedoplan.showcase.persistence;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.gedoplan.showcase.entity.Currency;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
// @Transactional
public class CurrencyRepository {
  private static final Map<String, Currency> CURRENCIES = Map.of(
    "CHF", new Currency("CHF", BigDecimal.valueOf(0.92843)),
    "CNY", new Currency("CNY", BigDecimal.valueOf(0.12443)),
    "GBP", new Currency("GBP", BigDecimal.valueOf(1.12250)),
    "JPY", new Currency("JPY", BigDecimal.valueOf(0.00819)),
    "USD", new Currency("USD", BigDecimal.valueOf(0.88195)));

  public List<Currency> findAll() {
    return new ArrayList<>(CURRENCIES.values());
  }

  public Currency findById(String id) {
    return CURRENCIES.get(id);
  }
}
