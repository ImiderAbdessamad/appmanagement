package ma.zyn.app.service.facade.admin.task;

import java.util.List;
import ma.zyn.app.bean.core.task.TaskState;
import ma.zyn.app.dao.criteria.core.task.TaskStateCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface TaskStateAdminService {







	TaskState create(TaskState t);

    TaskState update(TaskState t);

    List<TaskState> update(List<TaskState> ts,boolean createIfNotExist);

    TaskState findById(Long id);

    TaskState findOrSave(TaskState t);

    TaskState findByReferenceEntity(TaskState t);

    TaskState findWithAssociatedLists(Long id);

    List<TaskState> findAllOptimized();

    List<TaskState> findAll();

    List<TaskState> findByCriteria(TaskStateCriteria criteria);

    List<TaskState> findPaginatedByCriteria(TaskStateCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(TaskStateCriteria criteria);

    List<TaskState> delete(List<TaskState> ts);

    boolean deleteById(Long id);

    List<List<TaskState>> getToBeSavedAndToBeDeleted(List<TaskState> oldList, List<TaskState> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
