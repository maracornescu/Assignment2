package app;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import business.model.LaboratoryModel;
import controllers.LaboratoryController;

@RunWith(SpringRunner.class)

@SpringBootTest
@AutoConfigureMockMvc
public class LabManagerApplicationTests {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private LaboratoryController laboratoryController;
	
	
	@Test
	public void findLaboratoryById() throws Exception {
		LaboratoryModel laboratory = new LaboratoryModel();
		laboratory.setLaboratoryId((long) 1);
		laboratory.setLaboratoryNumber(1);
		laboratory.setTitle("Revision");
		laboratory.setCurricula("UML");
		laboratory.setLaboratoryText("https://github.com/coroveiandrei/UTCNSoftwareDesignLab/blob/master/lab1/L1_Revision.pdf");
		
		given(laboratoryController.getLaboratoryById((long) 1)).willReturn(laboratory);
		
		mvc.perform(get("/laboratory/{id}", 1)
		.contentType(APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("laboratoryNumber", is(laboratory.getLaboratoryNumber())))
		.andExpect(jsonPath("title", is(laboratory.getTitle())))
		.andExpect(jsonPath("curricula", is(laboratory.getCurricula())))
		.andExpect(jsonPath("laboratoryText", is(laboratory.getLaboratoryText())));
		
	}
	
	/*
	@Test
	public void findAllLaboratories() throws Exception {
		LaboratoryModel laboratory = new LaboratoryModel();
		laboratory.setLaboratoryId((long) 1);
		laboratory.setLaboratoryNumber(1);
		laboratory.setTitle("Revision");
		laboratory.setCurricula("UML");
		laboratory.setLaboratoryText("https://github.com/coroveiandrei/UTCNSoftwareDesignLab/blob/master/lab1/L1_Revision.pdf");
		
		String keyword = "UML";
		List<LaboratoryModel> laboratories = singletonList(laboratory);
		given(laboratoryController.getAllLaboratory(keyword)).willReturn(laboratories);
		
		mvc.perform(get("/laboratory")
		.contentType(APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$[0].laboratoryNumber", is(laboratory.getLaboratoryNumber())))
		.andExpect(jsonPath("$[0].title", is(laboratory.getTitle())))
		.andExpect(jsonPath("$[0].curricula", is(laboratory.getCurricula())))
		.andExpect(jsonPath("$[0].laboratoryText", is(laboratory.getLaboratoryText())));
		
	}
	*/
	
	@Test
	public void saveLaboratory() throws Exception {
		LaboratoryModel laboratory = new LaboratoryModel();
		laboratory.setLaboratoryId((long) 1);
		laboratory.setLaboratoryNumber(1);
		laboratory.setTitle("Revision");
		laboratory.setCurricula("UML");
		laboratory.setLaboratoryText("https://github.com/coroveiandrei/UTCNSoftwareDesignLab/blob/master/lab1/L1_Revision.pdf");
		
		when(laboratoryController.saveLaboratory(laboratory)).thenReturn(laboratory);
		
		mvc.perform(post("/laboratory")
				.contentType(APPLICATION_JSON)
				.content(asJsonString(laboratory)))
		.andExpect(status().isOk());
		//.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		//.andExpect(jsonPath("laboratoryNumber", is(laboratory.getLaboratoryNumber())))
		//.andExpect(jsonPath("title", is(laboratory.getTitle())))
		//.andExpect(jsonPath("curricula", is(laboratory.getCurricula())))
		//.andExpect(jsonPath("laboratoryText", is(laboratory.getLaboratoryText())));
		
		
	}
	
	 static String asJsonString(final Object obj) {
	        try {
	            return new ObjectMapper().writeValueAsString(obj);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	 }
	 
	 /*
	 //Testing PUT	 
	 @Test
	 public void testUpdateUser() throws Exception {
		 
		 //get mock data
		 UserDTO user = getMockUsers("Update").get(0);
		 
		 when(userServiceMock.saveUser(user)).thenReturn(user);
		 
		 this.mockMvc.perform(put("/api/users")
				 .contentType(MediaType.APPLICATION_JSON)
				 .content(asJsonString(user)))
		 .andExpect(status().isOk())
		 .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		 .andExpect(jsonPath("$.id",is(1)))
		 .andExpect(jsonPath("$.github",is(1)))
		 .andExpect(jsonPath("$.userName",is("test1UserName")))
		 .andExpect(jsonPath("$.firstName",is("test1FirstName")))
		 .andExpect(jsonPath("$.lastName",is("test1LastName")))
		 .andExpect(jsonPath("$.email",is("test1Email@gmail.com")))
		 .andExpect(jsonPath("$.phone",is("9542234567")))		 
		 .andExpect(jsonPath("$.status",is("A")))
		 .andExpect(jsonPath("$.role",is("V")))
		 .andExpect(jsonPath("$.state",is("CA")))
		 .andExpect(jsonPath("$.country",is("USA")));
		 
		 verify(userServiceMock, times(1)).saveUser(user);
	     verifyNoMoreInteractions(userServiceMock);
				 
	 }
	 
	 
	 //Testing DELETE
	 @Test	 
	 public void testDeleteUser() throws Exception
	 {
		 //get mock data
		 UserDTO user = getMockUsers("Delete").get(0);
		 
		 doNothing().when(userServiceMock).deleteUser(user.getId());
		 
		 this.mockMvc.perform(delete("/api/users/{id}",user.getId())
				 .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
		 .andExpect(status().isOk());
		 
		 verify(userServiceMock, times(1)).deleteUser(user.getId());
	     verifyNoMoreInteractions(userServiceMock);
	}
*/
}
