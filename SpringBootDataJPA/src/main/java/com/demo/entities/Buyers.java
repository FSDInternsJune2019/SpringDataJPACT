package com.demo.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name="buyers_table")
public class Buyers implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="buyers_id")
	private int buyersId;
	
	@Column(name="buyers_name")
	private String buyersName;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="buyers_products",joinColumns = {@JoinColumn(name="buyers_id")},
	inverseJoinColumns = {@JoinColumn(name="product_code")})
	private List<Products> products;

}
