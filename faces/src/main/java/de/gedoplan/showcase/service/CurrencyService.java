package de.gedoplan.showcase.service;

import java.io.Serializable;
import java.math.BigDecimal;

import de.gedoplan.showcase.entity.Currency;
import de.gedoplan.showcase.persistence.CurrencyRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CurrencyService implements Serializable {

  @Inject
  private CurrencyRepository currencyRepository;

  public BigDecimal convertToEuro(BigDecimal amount, String currencyId) {
    Currency currency = this.currencyRepository.findById(currencyId);
    if (currency == null) {
      throw new IllegalArgumentException("Currency " + currencyId + " unknown");
    }

    return convertToEuro(amount, currency);
  }

  public BigDecimal convertToEuro(BigDecimal amount, Currency currency) {
    return amount.multiply(currency.getEuroValue());
  }
}
