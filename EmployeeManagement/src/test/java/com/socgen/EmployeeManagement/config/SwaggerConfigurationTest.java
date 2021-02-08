package com.socgen.EmployeeManagement.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes=SwaggerConfiguration.class)
class SwaggerConfigurationTest {

	@Mock
	Docket docket;
	
	
	@Mock
	UiConfiguration uiconfiguration;
	
	@Test
	public void docketTest() {
		assertNotNull(docket);
	}
	
	@Test
	public void uiconfigurationTest() {
		assertNotNull(uiconfiguration);
	}

}
