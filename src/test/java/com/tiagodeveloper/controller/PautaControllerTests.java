package com.tiagodeveloper.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiagodeveloper.dto.PautaDTO;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class PautaControllerTests {
	
	ObjectMapper om = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	@Test
	@SqlGroup(
		value = {
			@Sql(
				executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, 
				scripts = "classpath:db/pauta/insert.sql"
			),
			@Sql(
				executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, 
				scripts = "classpath:db/pauta/delete.sql"
			)
		}
	)
	void getAllTest() throws Exception {
		
		mockMvc.perform(get("/pauta")
	            .contentType("application/json"))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.empty",equalTo(false)))
	            .andExpect(jsonPath("$.numberOfElements",equalTo(2)))
	            .andExpect(jsonPath("$.content[0].titulo",equalTo("Definicao de arquitetura")));
		
	}
	
	
	@Test
	void createTest() throws Exception {
		
		var expectedRecord = PautaDTO.builder()
					.titulo("Definicao de padroes arquiteturais")
					.descricao("...")
					.dataInicio(LocalDateTime.of(LocalDate.of(2022, 10, 15), LocalTime.of(9, 0)))
					.dataFim(LocalDateTime.of(LocalDate.of(2022, 10, 15), LocalTime.of(18, 0)))
				.build();
		
		var actualRecord = om.readValue(mockMvc.perform(post("/pauta")
	            .contentType("application/json")
	            .content(om.writeValueAsString(expectedRecord)))
	            .andDo(print())
	            .andExpect(jsonPath("$.id", greaterThan(0)))
	            .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString(), PautaDTO.class);

	    assertEquals(expectedRecord.getTitulo(), actualRecord.getTitulo());
		
	}

}
