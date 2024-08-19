package ma.zyn.app.dao.facade.core.task;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.task.Task;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface TaskDao extends AbstractRepository<Task,Long>  {

    List<Task> findByTaskStateCode(String code);
            public int deleteByTaskStateCode(String code);
    long countByTaskStateCode(String code);
    List<Task> findByAssignedToId(Long id);
    int deleteByAssignedToId(Long id);
    long countByAssignedToId(Long id);
    List<Task> findByProjectId(Long id);
    int deleteByProjectId(Long id);
    long countByProjectCode(String code);


}
