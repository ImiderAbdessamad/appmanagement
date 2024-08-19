package ma.zyn.app.dao.facade.core.project;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.project.ProjectType;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.project.ProjectType;
import java.util.List;


@Repository
public interface ProjectTypeDao extends AbstractRepository<ProjectType,Long>  {
    ProjectType findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW ProjectType(item.id,item.libelle) FROM ProjectType item")
    List<ProjectType> findAllOptimized();

}
