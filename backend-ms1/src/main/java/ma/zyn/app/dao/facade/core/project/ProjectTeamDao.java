package ma.zyn.app.dao.facade.core.project;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.project.ProjectTeam;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ProjectTeamDao extends AbstractRepository<ProjectTeam,Long>  {

    List<ProjectTeam> findByProjectId(Long id);
    int deleteByProjectId(Long id);
    long countByProjectCode(String code);


}
