package de.gedoplan.showcase.persistence;

import java.util.List;
import java.util.Optional;

import de.gedoplan.showcase.domain.Country;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class CountryRepository {

  @PersistenceContext
  EntityManager entityManager;

  public void persist(Country country) {
    this.entityManager.persist(country);
  }

  public Country merge(Country country) {
    return this.entityManager.merge(country);
  }

  public void removeById(String id) {
    findById(id).ifPresent(this.entityManager::remove);
  }

  public Optional<Country> findById(String id) {
    return Optional.ofNullable(this.entityManager.find(Country.class, id));
  }

  public List<Country> findAll() {
    return this.entityManager.createQuery("select x from Country x", Country.class).getResultList();
  }

  public List<Country> findByCarCode(String carCode) {
    return this.entityManager
        .createQuery("select x from Country x where x.carCode=?1", Country.class)
        .setParameter(1, carCode)
        .getResultList();
  }

  public List<Country> findByPhonePrefix(String phonePrefix) {
    return this.entityManager
        .createNamedQuery("Country.findByPhonePrefix", Country.class)
        .setParameter("phonePrefix", phonePrefix)
        .getResultList();
  }

}
