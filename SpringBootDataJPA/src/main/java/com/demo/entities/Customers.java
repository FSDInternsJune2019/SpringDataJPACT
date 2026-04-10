package com.demo.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name="customers_table")
public class Customers {
	@Id
	@Column(name="customers_id")
	private int CustomersId;
	@Column(name="customers_name")
	private String customersName;
	@OneToMany(targetEntity = Orders.class,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Orders> orders;

}
