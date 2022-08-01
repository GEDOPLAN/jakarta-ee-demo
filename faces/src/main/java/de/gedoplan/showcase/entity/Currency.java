package de.gedoplan.showcase.entity;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

//@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Currency {

  // @Id
  @EqualsAndHashCode.Include
  private String id;

  // @Column(columnDefinition = "DECIMAL(10,5)")
  private BigDecimal euroValue;

}
