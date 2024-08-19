package ma.zyn.app.unit.ws.facade.admin.project;

import ma.zyn.app.bean.core.project.ProjectTeam;
import ma.zyn.app.service.impl.admin.project.ProjectTeamAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.project.ProjectTeamRestAdmin;
import ma.zyn.app.ws.converter.project.ProjectTeamConverter;
import ma.zyn.app.ws.dto.project.ProjectTeamDto;
import org.aspectj.lang.annotation.Before;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProjectTeamRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private ProjectTeamAdminServiceImpl service;
    @Mock
    private ProjectTeamConverter converter;

    @InjectMocks
    private ProjectTeamRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllProjectTeamTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<ProjectTeamDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<ProjectTeamDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveProjectTeamTest() throws Exception {
        // Mock data
        ProjectTeamDto requestDto = new ProjectTeamDto();
        ProjectTeam entity = new ProjectTeam();
        ProjectTeam saved = new ProjectTeam();
        ProjectTeamDto savedDto = new ProjectTeamDto();

        // Mock the converter to return the projectTeam object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved projectTeam DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<ProjectTeamDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        ProjectTeamDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved projectTeam DTO
        assertEquals(savedDto.getName(), responseBody.getName());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
    }

}
