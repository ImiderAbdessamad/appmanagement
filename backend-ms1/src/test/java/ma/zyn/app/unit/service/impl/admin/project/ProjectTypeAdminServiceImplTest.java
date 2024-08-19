package ma.zyn.app.unit.service.impl.admin.project;

import ma.zyn.app.bean.core.project.ProjectType;
import ma.zyn.app.dao.facade.core.project.ProjectTypeDao;
import ma.zyn.app.service.impl.admin.project.ProjectTypeAdminServiceImpl;

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
class ProjectTypeAdminServiceImplTest {

    @Mock
    private ProjectTypeDao repository;
    private AutoCloseable autoCloseable;
    private ProjectTypeAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ProjectTypeAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllProjectType() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveProjectType() {
        // Given
        ProjectType toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteProjectType() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetProjectTypeById() {
        // Given
        Long idToRetrieve = 1L; // Example ProjectType ID to retrieve
        ProjectType expected = new ProjectType(); // You need to replace ProjectType with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        ProjectType result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private ProjectType constructSample(int i) {
		ProjectType given = new ProjectType();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        return given;
    }

}
