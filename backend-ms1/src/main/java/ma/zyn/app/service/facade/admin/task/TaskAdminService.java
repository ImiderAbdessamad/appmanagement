package ma.zyn.app.service.facade.admin.task;

import java.util.List;
import ma.zyn.app.bean.core.task.Task;
import ma.zyn.app.dao.criteria.core.task.TaskCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface TaskAdminService {



    List<Task> findByTaskStateCode(String code);
    int deleteByTaskStateCode(String code);
    long countByTaskStateCode(String code);
    List<Task> findByAssignedToId(Long id);
    int deleteByAssignedToId(Long id);
    long countByAssignedToId(Long id);
    List<Task> findByProjectId(Long id);
    int deleteByProjectId(Long id);
    long countByProjectCode(String code);




	Task create(Task t);

    Task update(Task t);

    List<Task> update(List<Task> ts,boolean createIfNotExist);

    Task findById(Long id);

    Task findOrSave(Task t);

    Task findByReferenceEntity(Task t);

    Task findWithAssociatedLists(Long id);

    List<Task> findAllOptimized();

    List<Task> findAll();

    List<Task> findByCriteria(TaskCriteria criteria);

    List<Task> findPaginatedByCriteria(TaskCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(TaskCriteria criteria);

    List<Task> delete(List<Task> ts);

    boolean deleteById(Long id);

    List<List<Task>> getToBeSavedAndToBeDeleted(List<Task> oldList, List<Task> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
