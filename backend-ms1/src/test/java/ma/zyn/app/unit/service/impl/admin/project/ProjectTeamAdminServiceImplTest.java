package ma.zyn.app.unit.service.impl.admin.project;

import ma.zyn.app.bean.core.project.ProjectTeam;
import ma.zyn.app.dao.facade.core.project.ProjectTeamDao;
import ma.zyn.app.service.impl.admin.project.ProjectTeamAdminServiceImpl;

import ma.zyn.app.bean.core.project.Project ;
import ma.zyn.app.bean.core.member.Member ;
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
class ProjectTeamAdminServiceImplTest {

    @Mock
    private ProjectTeamDao repository;
    private AutoCloseable autoCloseable;
    private ProjectTeamAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ProjectTeamAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllProjectTeam() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveProjectTeam() {
        // Given
        ProjectTeam toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteProjectTeam() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetProjectTeamById() {
        // Given
        Long idToRetrieve = 1L; // Example ProjectTeam ID to retrieve
        ProjectTeam expected = new ProjectTeam(); // You need to replace ProjectTeam with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        ProjectTeam result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private ProjectTeam constructSample(int i) {
		ProjectTeam given = new ProjectTeam();
        given.setName("name-"+i);
        given.setDescription("description-"+i);
        given.setProject(new Project(1L));
        List<Member> members = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                Member element = new Member();
                                                element.setId((long)id);
                                                element.setFirstName("firstName"+id);
                                                element.setLastName("lastName"+id);
                                                element.setEmail("email"+id);
                                                element.setPhone("phone"+id);
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setMembers(members);
        return given;
    }

}
