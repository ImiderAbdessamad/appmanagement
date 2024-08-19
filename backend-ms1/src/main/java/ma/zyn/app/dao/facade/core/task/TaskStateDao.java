package ma.zyn.app.dao.facade.core.task;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.task.TaskState;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.task.TaskState;
import java.util.List;


@Repository
public interface TaskStateDao extends AbstractRepository<TaskState,Long>  {
    TaskState findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW TaskState(item.id,item.libelle) FROM TaskState item")
    List<TaskState> findAllOptimized();

}
