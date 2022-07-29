package de.gedoplan.showcase.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = City.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class City {

  public static final String TABLE_NAME = "JPA_CITY";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cityGeneratorSequence")
  // @SequenceGenerator(name = "cityGeneratorSequence", sequenceName = "JPA_CITY_SEQUENCE", allocationSize = 100)
  // @GeneratedValue(strategy = GenerationType.TABLE, generator = "cityGeneratorTable")
  // @TableGenerator(name = "cityGeneratorTable", table = "JPA_CITY_GEN", pkColumnName = "GENERATOR", pkColumnValue = "City", valueColumnName = "ID", allocationSize = 100)
  private Integer id;

  private String name;

  private int population;

  private int area;

  @Override
  public int hashCode() {
    return (this.id == null) ? 0 : this.id.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    City other = (City) obj;
    if (this.id == null) {
      return false;
    }
    return this.id.equals(other.id);
  }
}
