package ma.zyn.app.dao.facade.core.project;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.project.Project;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.project.Project;
import java.util.List;


@Repository
public interface ProjectDao extends AbstractRepository<Project,Long>  {
    Project findByCode(String code);
    int deleteByCode(String code);

    List<Project> findByProjectStateCode(String code);
            public int deleteByProjectStateCode(String code);
    long countByProjectStateCode(String code);
    List<Project> findByProjectTeamId(Long id);
    int deleteByProjectTeamId(Long id);
    long countByProjectTeamId(Long id);
    List<Project> findByProjectTypeId(Long id);
    int deleteByProjectTypeId(Long id);
    long countByProjectTypeCode(String code);
    List<Project> findByProjectCategoryId(Long id);
    int deleteByProjectCategoryId(Long id);
    long countByProjectCategoryCode(String code);

    @Query("SELECT NEW Project(item.id,item.code) FROM Project item")
    List<Project> findAllOptimized();

}
