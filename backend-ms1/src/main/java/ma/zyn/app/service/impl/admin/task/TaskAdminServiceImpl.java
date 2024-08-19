package ma.zyn.app.service.impl.admin.task;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.task.Task;
import ma.zyn.app.dao.criteria.core.task.TaskCriteria;
import ma.zyn.app.dao.facade.core.task.TaskDao;
import ma.zyn.app.dao.specification.core.task.TaskSpecification;
import ma.zyn.app.service.facade.admin.task.TaskAdminService;
import ma.zyn.app.zynerator.service.AbstractServiceImpl;
import static ma.zyn.app.zynerator.util.ListUtil.*;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.zyn.app.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.zyn.app.service.facade.admin.project.ProjectAdminService ;
import ma.zyn.app.bean.core.project.Project ;
import ma.zyn.app.service.facade.admin.task.TaskStateAdminService ;
import ma.zyn.app.bean.core.task.TaskState ;
import ma.zyn.app.service.facade.admin.member.MemberAdminService ;
import ma.zyn.app.bean.core.member.Member ;

import java.util.List;
@Service
public class TaskAdminServiceImpl implements TaskAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Task update(Task t) {
        Task loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Task.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public Task findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Task findOrSave(Task t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Task result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Task> findAll() {
        return dao.findAll();
    }

    public List<Task> findByCriteria(TaskCriteria criteria) {
        List<Task> content = null;
        if (criteria != null) {
            TaskSpecification mySpecification = constructSpecification(criteria);
            if (criteria.isPeagable()) {
                Pageable pageable = PageRequest.of(0, criteria.getMaxResults());
                content = dao.findAll(mySpecification, pageable).getContent();
            } else {
                content = dao.findAll(mySpecification);
            }
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private TaskSpecification constructSpecification(TaskCriteria criteria) {
        TaskSpecification mySpecification =  (TaskSpecification) RefelexivityUtil.constructObjectUsingOneParam(TaskSpecification.class, criteria);
        return mySpecification;
    }

    public List<Task> findPaginatedByCriteria(TaskCriteria criteria, int page, int pageSize, String order, String sortField) {
        TaskSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(TaskCriteria criteria) {
        TaskSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Task> findByTaskStateCode(String code){
        return dao.findByTaskStateCode(code);
    }
    public int deleteByTaskStateCode(String code){
        return dao.deleteByTaskStateCode(code);
    }
    public long countByTaskStateCode(String code){
        return dao.countByTaskStateCode(code);
    }
    public List<Task> findByAssignedToId(Long id){
        return dao.findByAssignedToId(id);
    }
    public int deleteByAssignedToId(Long id){
        return dao.deleteByAssignedToId(id);
    }
    public long countByAssignedToId(Long id){
        return dao.countByAssignedToId(id);
    }
    public List<Task> findByProjectId(Long id){
        return dao.findByProjectId(id);
    }
    public int deleteByProjectId(Long id){
        return dao.deleteByProjectId(id);
    }
    public long countByProjectCode(String code){
        return dao.countByProjectCode(code);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            deleteAssociatedLists(id);
            dao.deleteById(id);
        }
        return condition;
    }

    public void deleteAssociatedLists(Long id) {
deleteByTaskId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Task> delete(List<Task> list) {
		List<Task> result = new ArrayList();
        if (list != null) {
            for (Task t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Task create(Task t) {
        Task loaded = findByReferenceEntity(t);
        Task saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getSubTasks() != null) {
                t.getSubTasks().forEach(element-> {
                    element.setTask(saved);
                    taskService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

    public Task findWithAssociatedLists(Long id){
        Task result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setSubTasks(findByTaskId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Task> update(List<Task> ts, boolean createIfNotExist) {
        List<Task> result = new ArrayList<>();
        if (ts != null) {
            for (Task t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Task loadedItem = dao.findById(t.getId()).orElse(null);
                    if (isEligibleForCreateOrUpdate(createIfNotExist, t, loadedItem)) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Task t, Task loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(Task task){
    if(task !=null && task.getId() != null){
        List<List<Task>> resultSubTasks= taskService.getToBeSavedAndToBeDeleted(taskService.findByTaskId(task.getId()),task.getSubTasks());
            delete(resultSubTasks.get(1));
        emptyIfNull(resultSubTasks.get(0)).forEach(e -> e.setTask(task));
        taskService.update(resultSubTasks.get(0),true);
        }
    }








    public Task findByReferenceEntity(Task t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Task t){
        if( t != null) {
            t.setTaskState(taskStateService.findOrSave(t.getTaskState()));
            t.setAssignedTo(memberService.findOrSave(t.getAssignedTo()));
            t.setProject(projectService.findOrSave(t.getProject()));
        }
    }



    public List<Task> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Task>> getToBeSavedAndToBeDeleted(List<Task> oldList, List<Task> newList) {
        List<List<Task>> result = new ArrayList<>();
        List<Task> resultDelete = new ArrayList<>();
        List<Task> resultUpdateOrSave = new ArrayList<>();
        if (isEmpty(oldList) && isNotEmpty(newList)) {
            resultUpdateOrSave.addAll(newList);
        } else if (isEmpty(newList) && isNotEmpty(oldList)) {
            resultDelete.addAll(oldList);
        } else if (isNotEmpty(newList) && isNotEmpty(oldList)) {
			extractToBeSaveOrDelete(oldList, newList, resultUpdateOrSave, resultDelete);
        }
        result.add(resultUpdateOrSave);
        result.add(resultDelete);
        return result;
    }

    private void extractToBeSaveOrDelete(List<Task> oldList, List<Task> newList, List<Task> resultUpdateOrSave, List<Task> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Task myOld = oldList.get(i);
                Task t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Task myNew = newList.get(i);
                Task t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }







    @Autowired
    private ProjectAdminService projectService ;
    @Autowired
    private TaskStateAdminService taskStateService ;
    @Autowired
    private MemberAdminService memberService ;

    public TaskAdminServiceImpl(TaskDao dao) {
        this.dao = dao;
    }

    private TaskDao dao;
}
