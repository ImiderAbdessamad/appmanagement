package ma.zyn.app.dao.facade.core.project;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.project.ProjectCategory;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.project.ProjectCategory;
import java.util.List;


@Repository
public interface ProjectCategoryDao extends AbstractRepository<ProjectCategory,Long>  {
    ProjectCategory findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW ProjectCategory(item.id,item.libelle) FROM ProjectCategory item")
    List<ProjectCategory> findAllOptimized();

}
