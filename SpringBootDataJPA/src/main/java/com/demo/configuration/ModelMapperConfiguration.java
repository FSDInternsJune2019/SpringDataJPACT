package com.demo.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {
	@Bean
	public ModelMapper mapper() {
		ModelMapper modelMapper=new ModelMapper();
		/*
		// Skip ID and Version during DTO -> Entity mapping
        modelMapper.typeMap(EmployeesDTO.class, Employees.class).addMappings(mapper -> {
            mapper.skip(Employees::setEmpId);
            mapper.skip(Employees::setVersion); // Assuming your field is named 'version'
        });*/
        modelMapper.getConfiguration().setSkipNullEnabled(true);
		return modelMapper;
	}

}
