package de.gedoplan.showcase.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NamedQuery(name = "Country.findByPhonePrefix", query = "select c from Country c where c.phonePrefix=:phonePrefix")
@Table(name = Country.TABLE_NAME, uniqueConstraints = @UniqueConstraint(columnNames = "NAME"), indexes = @Index(columnList = "PHONE_PREFIX"))
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Country {
  public static final String TABLE_NAME = "JPA_COUNTRY";

  @Id
  @Column(name = "ISO_CODE", length = 2)
  @EqualsAndHashCode.Include
  private String isoCode;

  private String name;

  @Column(name = "PHONE_PREFIX", length = 5)
  private String phonePrefix;

  @Column(name = "CAR_CODE", length = 3)
  private String carCode;

  private long population;

  @Enumerated(EnumType.STRING)
  private Continent continent;

  private boolean expired;

  public Country(String isoCode, String name, String phonePrefix, String carCode, long population, Continent continent) {
    this.isoCode = isoCode;
    this.name = name;
    this.phonePrefix = phonePrefix;
    this.carCode = carCode;
    this.population = population;
    this.continent = continent;
  }

  public Country(String isoCode, String name, String phonePrefix, String carCode, long population, Continent continent, boolean expired) {
    this(isoCode, name, phonePrefix, carCode, population, continent);
    this.expired = expired;
  }

}
