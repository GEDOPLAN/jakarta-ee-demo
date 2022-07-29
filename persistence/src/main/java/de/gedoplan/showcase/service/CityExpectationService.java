package de.gedoplan.showcase.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import de.gedoplan.showcase.domain.City;
import de.gedoplan.showcase.persistence.CityRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.UserTransaction;

/**
 * Checker for some expectations about entity object equality.
 *
 * Try the following scenarios:
 *
 * <ol>
 * <li>Use <code>@GeneratedValue</code> and <code>Integer</code> for {@link City#id}. <br/>
 * Do not implement <code>hashcode</code> and <code>equals</code>.</li>
 * <li>Same as 1., but use a standard implementation of <code>hashcode</code> and <code>equals</code> using only the <code>id</code> field.</li>
 * <li>Same as 2., but modify <code>equals</code>, returning <code>false</code>, if <code>id</code> is unset.</li>
 * <li>Use <code>UUID</code> for {@link City#id}. <br/>
 * Do not use <code>@GeneratedValue</code>. <br/>
 * Implement <code>hashcode</code> and <code>equals</code> as in 2.</li>
 * </ol>
 *
 * Only the last version meets every expectation!
 */
@ApplicationScoped
public class CityExpectationService {

  @Inject
  UserTransaction userTransaction;

  @Inject
  CityRepository cityRepository;

  public Map<String, Boolean> check() {
    try {
      Map<String, Boolean> checks = new HashMap<>();

      Set<City> cities = new HashSet<>();
      City london = new City("London", 8982000, 1572);
      cities.add(london);
      cities.add(new City("Madrid", 3223000, 604));
      cities.add(new City("Paris", 2161000, 105));

      checks.put("a) Set accepts multipe transient objects", cities.size() > 1);

      this.userTransaction.begin();
      cities.forEach(this.cityRepository::persist);
      this.userTransaction.commit();

      checks.put("b) Hash sets remain intact after persisting their entries", cities.contains(london));

      checks.put("c) Objects remain equal in different entity managers", this.cityRepository.findAll().contains(london));

      return checks;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
