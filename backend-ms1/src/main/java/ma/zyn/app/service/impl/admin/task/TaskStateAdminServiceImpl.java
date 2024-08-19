package ma.zyn.app.service.impl.admin.task;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.task.TaskState;
import ma.zyn.app.dao.criteria.core.task.TaskStateCriteria;
import ma.zyn.app.dao.facade.core.task.TaskStateDao;
import ma.zyn.app.dao.specification.core.task.TaskStateSpecification;
import ma.zyn.app.service.facade.admin.task.TaskStateAdminService;
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


import java.util.List;
@Service
public class TaskStateAdminServiceImpl implements TaskStateAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TaskState update(TaskState t) {
        TaskState loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{TaskState.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public TaskState findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public TaskState findOrSave(TaskState t) {
        if (t != null) {
            TaskState result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<TaskState> findAll() {
        return dao.findAll();
    }

    public List<TaskState> findByCriteria(TaskStateCriteria criteria) {
        List<TaskState> content = null;
        if (criteria != null) {
            TaskStateSpecification mySpecification = constructSpecification(criteria);
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


    private TaskStateSpecification constructSpecification(TaskStateCriteria criteria) {
        TaskStateSpecification mySpecification =  (TaskStateSpecification) RefelexivityUtil.constructObjectUsingOneParam(TaskStateSpecification.class, criteria);
        return mySpecification;
    }

    public List<TaskState> findPaginatedByCriteria(TaskStateCriteria criteria, int page, int pageSize, String order, String sortField) {
        TaskStateSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(TaskStateCriteria criteria) {
        TaskStateSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            dao.deleteById(id);
        }
        return condition;
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TaskState> delete(List<TaskState> list) {
		List<TaskState> result = new ArrayList();
        if (list != null) {
            for (TaskState t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TaskState create(TaskState t) {
        TaskState loaded = findByReferenceEntity(t);
        TaskState saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public TaskState findWithAssociatedLists(Long id){
        TaskState result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TaskState> update(List<TaskState> ts, boolean createIfNotExist) {
        List<TaskState> result = new ArrayList<>();
        if (ts != null) {
            for (TaskState t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    TaskState loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, TaskState t, TaskState loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public TaskState findByReferenceEntity(TaskState t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<TaskState> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<TaskState>> getToBeSavedAndToBeDeleted(List<TaskState> oldList, List<TaskState> newList) {
        List<List<TaskState>> result = new ArrayList<>();
        List<TaskState> resultDelete = new ArrayList<>();
        List<TaskState> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<TaskState> oldList, List<TaskState> newList, List<TaskState> resultUpdateOrSave, List<TaskState> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                TaskState myOld = oldList.get(i);
                TaskState t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                TaskState myNew = newList.get(i);
                TaskState t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public TaskStateAdminServiceImpl(TaskStateDao dao) {
        this.dao = dao;
    }

    private TaskStateDao dao;
}
