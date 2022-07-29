package de.gedoplan.showcase.persistence;

import java.util.List;

import de.gedoplan.showcase.domain.City;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class CityRepository {

  @PersistenceContext
  EntityManager entityManager;

  public void persist(City City) {
    this.entityManager.persist(City);
  }

  public List<City> findAll() {
    return this.entityManager.createQuery("select x from City x", City.class).getResultList();
  }

  public void flush() {
    this.entityManager.flush();
  }

}
