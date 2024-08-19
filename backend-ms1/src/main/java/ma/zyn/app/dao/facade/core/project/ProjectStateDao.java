package ma.zyn.app.dao.facade.core.project;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.project.ProjectState;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.project.ProjectState;
import java.util.List;


@Repository
public interface ProjectStateDao extends AbstractRepository<ProjectState,Long>  {
    ProjectState findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW ProjectState(item.id,item.libelle) FROM ProjectState item")
    List<ProjectState> findAllOptimized();

}
