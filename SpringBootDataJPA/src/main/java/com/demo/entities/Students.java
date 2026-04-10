package com.demo.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
@Table(name="students_table")
public class Students {
	@Id
	@Column(name="roll_no")
	private int rollNo;
	@Column(name="student_name")
	private String studentName;
	@ManyToOne(targetEntity = Batches.class,cascade = CascadeType.ALL)
	private Batches batches;
}
