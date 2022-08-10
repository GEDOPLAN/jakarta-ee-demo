package de.gedoplan.showcase.converter;

import de.gedoplan.showcase.entity.Currency;
import de.gedoplan.showcase.persistence.CurrencyRepository;
import jakarta.enterprise.context.Dependent;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

// A scope annotation is needed because of bean-discovery-mode=annotated
@Dependent
@FacesConverter(forClass = Currency.class, managed = true)
public class CurrencyConverter implements Converter<Currency> {
  @Inject
  private CurrencyRepository currencyRepository;

  @Override
  public Currency getAsObject(FacesContext context, UIComponent component, String value) {
    if (value == null) return null;
    return currencyRepository.findById(value);
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Currency value) {
    if (value == null) return null;
    return value.getId();
  }
}
