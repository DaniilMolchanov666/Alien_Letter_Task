package com.example.lanit_test_task;

import com.example.lanit_test_task.controller.XmlController;
import com.example.lanit_test_task.dto.FormattedAlienLetterDto;
import com.example.lanit_test_task.mapper.AlienLetterMapper;
import com.example.lanit_test_task.mapper.AlienLetterMapperImpl;
import com.example.lanit_test_task.model.AlienLetter;
import com.example.lanit_test_task.service.XmlLetterConverterService;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.File;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Import(TestConfig.class)
class XmlControllerTest {

	private MockMvc mockMvc;

	@Mock
	private XmlLetterConverterService letterConverterService;

	@InjectMocks
	private XmlController xmlController;

	@Autowired
	private XmlMapper xmlMapper;

	@Autowired
	private AlienLetterMapperImpl alienLetterMapper;

	@Test
	void generateLetter_ShouldReturnXmlResponse() throws Exception {

		AlienLetter alienLetter = xmlMapper.readValue(new File("alien_letter.xml"), AlienLetter.class);
		// Arrange
		FormattedAlienLetterDto mockDto = alienLetterMapper.toAlienLetterDto(alienLetter);
		when(letterConverterService.getFormattedAlienLetterDto()).thenReturn(mockDto);

		// Act & Assert
		mockMvc.perform(post("/")
						.accept(MediaType.APPLICATION_XML))
				.andExpectAll(
						status().isOk(),
						content().contentTypeCompatibleWith(MediaType.APPLICATION_XML),
						model().attributeExists("data"),
						model().attribute("data", mockDto),
						view().name("formatted_letter_template.xml")
				);
	}

	@Test
	void generateLetter_ShouldCallServiceOnce() throws Exception {
		// Arrange
		AlienLetter alienLetter = xmlMapper.readValue(new File("alien_letter.xml"), AlienLetter.class);
		FormattedAlienLetterDto mockDto = alienLetterMapper.toAlienLetterDto(alienLetter);

		when(letterConverterService.getFormattedAlienLetterDto())
				.thenReturn(mockDto);

		// Act
		mockMvc.perform(post("/"));

		// Assert
		verify(letterConverterService, times(1)).getFormattedAlienLetterDto();
		verifyNoMoreInteractions(letterConverterService);
	}

	@Test
	void generateLetter_ShouldReturn500WhenServiceFails() throws Exception {
		// Arrange
		when(letterConverterService.getFormattedAlienLetterDto())
				.thenThrow(new RuntimeException("Service error"));

		// Act & Assert
		mockMvc.perform(post("/"))
				.andExpect(status().isInternalServerError());
	}
}