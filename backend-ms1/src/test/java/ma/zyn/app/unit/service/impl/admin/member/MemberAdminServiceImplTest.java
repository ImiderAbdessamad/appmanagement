package ma.zyn.app.unit.service.impl.admin.member;

import ma.zyn.app.bean.core.member.Member;
import ma.zyn.app.dao.facade.core.member.MemberDao;
import ma.zyn.app.service.impl.admin.member.MemberAdminServiceImpl;

import ma.zyn.app.bean.core.project.Project ;
import ma.zyn.app.bean.core.project.ProjectTeam ;
import ma.zyn.app.bean.core.role.Role ;
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
class MemberAdminServiceImplTest {

    @Mock
    private MemberDao repository;
    private AutoCloseable autoCloseable;
    private MemberAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new MemberAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllMember() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveMember() {
        // Given
        Member toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteMember() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetMemberById() {
        // Given
        Long idToRetrieve = 1L; // Example Member ID to retrieve
        Member expected = new Member(); // You need to replace Member with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Member result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Member constructSample(int i) {
		Member given = new Member();
        given.setFirstName("firstName-"+i);
        given.setLastName("lastName-"+i);
        given.setEmail("email-"+i);
        given.setPhone("phone-"+i);
        List<Role> roles = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                Role element = new Role();
                                                element.setId((long)id);
                                                element.setCode("code"+id);
                                                element.setLibelle("libelle"+id);
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setRoles(roles);
        List<ProjectTeam> projectTeams = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                ProjectTeam element = new ProjectTeam();
                                                element.setId((long)id);
                                                element.setName("name"+id);
                                                element.setDescription("description"+id);
                                                element.setProject(new Project(Long.valueOf(3)));
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setProjectTeams(projectTeams);
        return given;
    }

}
