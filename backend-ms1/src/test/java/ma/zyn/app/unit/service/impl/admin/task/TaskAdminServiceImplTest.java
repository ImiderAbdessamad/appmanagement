package ma.zyn.app.unit.service.impl.admin.task;

import ma.zyn.app.bean.core.task.Task;
import ma.zyn.app.dao.facade.core.task.TaskDao;
import ma.zyn.app.service.impl.admin.task.TaskAdminServiceImpl;

import ma.zyn.app.bean.core.project.Project ;
import ma.zyn.app.bean.core.task.TaskState ;
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
class TaskAdminServiceImplTest {

    @Mock
    private TaskDao repository;
    private AutoCloseable autoCloseable;
    private TaskAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new TaskAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllTask() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveTask() {
        // Given
        Task toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteTask() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetTaskById() {
        // Given
        Long idToRetrieve = 1L; // Example Task ID to retrieve
        Task expected = new Task(); // You need to replace Task with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Task result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Task constructSample(int i) {
		Task given = new Task();
        given.setName("name-"+i);
        given.setDescription("description-"+i);
        given.setStartDate(LocalDateTime.now());
        given.setEndDate(LocalDateTime.now());
        given.setTaskState(new TaskState(1L));
        given.setAssignedTo(new Member(1L));
        given.setProject(new Project(1L));
        List<Task> subTasks = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                Task element = new Task();
                                                element.setId((long)id);
                                                element.setName("name"+id);
                                                element.setDescription("description"+id);
                                                element.setStartDate(LocalDateTime.now());
                                                element.setEndDate(LocalDateTime.now());
                                                element.setTaskState(new TaskState(Long.valueOf(5)));
                                                element.setAssignedTo(new Member(Long.valueOf(6)));
                                                element.setProject(new Project(Long.valueOf(7)));
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setSubTasks(subTasks);
        return given;
    }

}
