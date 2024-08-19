package ma.zyn.app.unit.dao.facade.core.project;

import ma.zyn.app.bean.core.project.ProjectType;
import ma.zyn.app.dao.facade.core.project.ProjectTypeDao;

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


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ProjectTypeDaoTest {

@Autowired
    private ProjectTypeDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        ProjectType entity = new ProjectType();
        entity.setCode(code);
        underTest.save(entity);
        ProjectType loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        ProjectType loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        ProjectType entity = new ProjectType();
        entity.setId(id);
        underTest.save(entity);
        ProjectType loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        ProjectType entity = new ProjectType();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        ProjectType loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<ProjectType> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<ProjectType> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        ProjectType given = constructSample(1);
        ProjectType saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private ProjectType constructSample(int i) {
		ProjectType given = new ProjectType();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        return given;
    }

}
