package com.demo.entities;

import java.io.Serializable;
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
@Table(name="orders_table")
public class Orders implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="orders_id")
	private String ordersId;
	@Column(name="orders_description")
	private String ordersDescription;
	@OneToMany(targetEntity = Items.class,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Items> items;

}
