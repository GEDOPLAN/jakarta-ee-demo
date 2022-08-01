package de.gedoplan.showcase.faces;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import de.gedoplan.showcase.entity.Currency;
import de.gedoplan.showcase.persistence.CurrencyRepository;
import de.gedoplan.showcase.service.CurrencyService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class CurrencyPresenter implements Serializable {

  private String currencyId = "USD";

  public String getCurrencyId() {
    return this.currencyId;
  }

  public void setCurrencyId(String currencyId) {
    this.currencyId = currencyId;
  }

  @Inject
  private CurrencyRepository currencyRepository;

  // public List<Currency> getCurrencies() {
  // return this.currencyRepository.findAll();
  // }

  /*
   * When following the exercise instructions, getCurrencies would just delegate to currencyRepository.findAll() - like shown in
   * the comment above.
   * That would cause multiple database accesses per single request. Therefore it is advisable to get the currencies in a
   *
   * @PostConstruct method just once:
   */

  private List<Currency> currencies;

  public List<Currency> getCurrencies() {
    return this.currencies;
  }

  @PostConstruct
  private void init() {
    this.currencies = this.currencyRepository.findAll();
  }

  @Inject
  private CurrencyService currencyService;

  private BigDecimal currencyAmount;

  private BigDecimal euroAmount;

  public BigDecimal getCurrencyAmount() {
    return this.currencyAmount;
  }

  public void setCurrencyAmount(BigDecimal currencyAmount) {
    this.currencyAmount = currencyAmount;
  }

  public BigDecimal getEuroAmount() {
    return this.euroAmount;
  }

  public void convert() {
    this.euroAmount = this.currencyService.convertToEuro(this.currencyAmount, this.currencyId);
  }

}
