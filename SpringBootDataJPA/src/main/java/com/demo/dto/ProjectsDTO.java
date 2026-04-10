package com.demo.dto;

import com.demo.enums.TYPE;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
public class ProjectsDTO {
	@NotBlank(message = "{com.demo.dto.ProjectsDTO.projectCode.blank}")
	@Pattern(regexp = "^P-\\d+$",message="{com.demo.dto.ProjectsDTO.projectCode.error}")
	private String projectCode;
	@NotBlank(message = "{com.demo.dto.ProjectsDTO.projectName.blank}")
	@Pattern(regexp="^[a-zA-Z ]+$",message="{com.demo.dto.ProjectsDTO.projectName.error}")
	private String projectName;
	private TYPE projectType;
}
