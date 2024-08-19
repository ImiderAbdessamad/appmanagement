package ma.zyn.app.unit.ws.facade.admin.member;

import ma.zyn.app.bean.core.member.Member;
import ma.zyn.app.service.impl.admin.member.MemberAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.member.MemberRestAdmin;
import ma.zyn.app.ws.converter.member.MemberConverter;
import ma.zyn.app.ws.dto.member.MemberDto;
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
public class MemberRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private MemberAdminServiceImpl service;
    @Mock
    private MemberConverter converter;

    @InjectMocks
    private MemberRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllMemberTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<MemberDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<MemberDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveMemberTest() throws Exception {
        // Mock data
        MemberDto requestDto = new MemberDto();
        Member entity = new Member();
        Member saved = new Member();
        MemberDto savedDto = new MemberDto();

        // Mock the converter to return the member object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved member DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<MemberDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        MemberDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved member DTO
        assertEquals(savedDto.getFirstName(), responseBody.getFirstName());
        assertEquals(savedDto.getLastName(), responseBody.getLastName());
        assertEquals(savedDto.getEmail(), responseBody.getEmail());
        assertEquals(savedDto.getPhone(), responseBody.getPhone());
    }

}
