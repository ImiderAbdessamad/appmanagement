package ma.zyn.app.unit.service.impl.admin.role;

import ma.zyn.app.bean.core.role.Role;
import ma.zyn.app.dao.facade.core.role.RoleDao;
import ma.zyn.app.service.impl.admin.role.RoleAdminServiceImpl;

import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;



import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class RoleAdminServiceImplTest {

    @Mock
    private RoleDao repository;
    private AutoCloseable autoCloseable;
    private RoleAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new RoleAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllRole() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveRole() {
        // Given
        Role toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteRole() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetRoleById() {
        // Given
        Long idToRetrieve = 1L; // Example Role ID to retrieve
        Role expected = new Role(); // You need to replace Role with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Role result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Role constructSample(int i) {
		Role given = new Role();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        return given;
    }

}
