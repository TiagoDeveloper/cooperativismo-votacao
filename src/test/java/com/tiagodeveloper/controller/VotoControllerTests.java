package com.tiagodeveloper.controller;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiagodeveloper.dto.PautaDTO;
import com.tiagodeveloper.dto.UsuarioDTO;
import com.tiagodeveloper.dto.VotoDTO;
import com.tiagodeveloper.enums.UserStatus;
import com.tiagodeveloper.enums.VotoEnum;
import com.tiagodeveloper.feign.client.UserClient;
import com.tiagodeveloper.service.PautaService;


@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class VotoControllerTests {
	
	
	ObjectMapper om = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserClient usuarioClient;
	
	@MockBean
	private PautaService pautaService;
	
	
	@Test
	void createTest() throws Exception {
		
		when(usuarioClient.getByDocument(any())).thenReturn(
			UsuarioDTO.builder()
				.status(UserStatus.ABLE_TO_VOTE)
			.build()
		);
		
		when(pautaService.getById(any())).thenReturn(
			PautaDTO.builder()
			.id(1)
			.titulo("Teste titulo")
			.descricao("Teste descricao")
			.dataInicio(LocalDateTime.now().minusDays(1))
			.dataFim(LocalDateTime.now().plusDays(1))
			.build()
		);
		
		var expectedRecord = VotoDTO.builder()
					.pauta(PautaDTO.builder().id(1).build())
					.voto(VotoEnum.SIM)
					.documento("00209035021")
				.build();
		
		var actualRecord = om.readValue(mockMvc.perform(post("/voto")
	            .contentType("application/json")
	            .content(om.writeValueAsString(expectedRecord)))
	            .andDo(print())
	            .andExpect(jsonPath("$.id", greaterThan(0)))
	            .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString(), VotoDTO.class);

	    assertEquals(expectedRecord.getDocumento(), actualRecord.getDocumento());
		
	}

}
