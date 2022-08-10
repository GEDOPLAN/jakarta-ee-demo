package de.gedoplan.showcase.domain;

import java.util.UUID;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = City.TABLE_NAME)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class City {

  public static final String TABLE_NAME = "JPA_CITY";

  @Id
  // @GeneratedValue
  // @GeneratedValue(strategy = GenerationType.IDENTITY)
  // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cityGeneratorSequence")
  // @SequenceGenerator(name = "cityGeneratorSequence", sequenceName = "JPA_CITY_SEQUENCE", allocationSize = 100)
  // @GeneratedValue(strategy = GenerationType.TABLE, generator = "cityGeneratorTable")
  // @TableGenerator(name = "cityGeneratorTable", table = "JPA_CITY_GEN", pkColumnName = "GENERATOR", pkColumnValue = "City", valueColumnName = "ID", allocationSize = 100)
  @EqualsAndHashCode.Include
  // private Integer id;
  private UUID id;

  private String name;

  private int population;

  private int area;

  @JsonbCreator
  public City(String name, int population, int area) {
    this.name = name;
    this.population = population;
    this.area = area;

    this.id = UUID.randomUUID();
  }

}
