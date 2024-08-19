package ma.zyn.app.unit.dao.facade.core.project;

import ma.zyn.app.bean.core.project.ProjectTeam;
import ma.zyn.app.dao.facade.core.project.ProjectTeamDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.time.LocalDateTime;

import ma.zyn.app.bean.core.project.Project ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ProjectTeamDaoTest {

@Autowired
    private ProjectTeamDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        ProjectTeam entity = new ProjectTeam();
        entity.setId(id);
        underTest.save(entity);
        ProjectTeam loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        ProjectTeam entity = new ProjectTeam();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        ProjectTeam loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<ProjectTeam> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<ProjectTeam> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        ProjectTeam given = constructSample(1);
        ProjectTeam saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private ProjectTeam constructSample(int i) {
		ProjectTeam given = new ProjectTeam();
        given.setName("name-"+i);
        given.setDescription("description-"+i);
        given.setProject(new Project(1L));
        return given;
    }

}
