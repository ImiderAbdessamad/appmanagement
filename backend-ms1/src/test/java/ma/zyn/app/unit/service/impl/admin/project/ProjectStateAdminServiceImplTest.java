package ma.zyn.app.unit.service.impl.admin.project;

import ma.zyn.app.bean.core.project.ProjectState;
import ma.zyn.app.dao.facade.core.project.ProjectStateDao;
import ma.zyn.app.service.impl.admin.project.ProjectStateAdminServiceImpl;

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
class ProjectStateAdminServiceImplTest {

    @Mock
    private ProjectStateDao repository;
    private AutoCloseable autoCloseable;
    private ProjectStateAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ProjectStateAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllProjectState() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveProjectState() {
        // Given
        ProjectState toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteProjectState() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetProjectStateById() {
        // Given
        Long idToRetrieve = 1L; // Example ProjectState ID to retrieve
        ProjectState expected = new ProjectState(); // You need to replace ProjectState with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        ProjectState result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private ProjectState constructSample(int i) {
		ProjectState given = new ProjectState();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setStyle("style-"+i);
        return given;
    }

}
