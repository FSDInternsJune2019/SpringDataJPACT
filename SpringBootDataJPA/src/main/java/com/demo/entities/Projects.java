package com.demo.entities;

import java.io.Serializable;

import com.demo.enums.TYPE;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "projects_table")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Projects implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="project_code")
	private String projectCode;
	
	@Column(name="project_name")
	private String projectName;
	
	@Enumerated(EnumType.STRING)
	@Column(name="project_type")
	private TYPE projectType;

}
