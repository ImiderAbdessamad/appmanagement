package ma.zyn.app.service.impl.admin.project;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.project.ProjectState;
import ma.zyn.app.dao.criteria.core.project.ProjectStateCriteria;
import ma.zyn.app.dao.facade.core.project.ProjectStateDao;
import ma.zyn.app.dao.specification.core.project.ProjectStateSpecification;
import ma.zyn.app.service.facade.admin.project.ProjectStateAdminService;
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
public class ProjectStateAdminServiceImpl implements ProjectStateAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProjectState update(ProjectState t) {
        ProjectState loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{ProjectState.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public ProjectState findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public ProjectState findOrSave(ProjectState t) {
        if (t != null) {
            ProjectState result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ProjectState> findAll() {
        return dao.findAll();
    }

    public List<ProjectState> findByCriteria(ProjectStateCriteria criteria) {
        List<ProjectState> content = null;
        if (criteria != null) {
            ProjectStateSpecification mySpecification = constructSpecification(criteria);
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


    private ProjectStateSpecification constructSpecification(ProjectStateCriteria criteria) {
        ProjectStateSpecification mySpecification =  (ProjectStateSpecification) RefelexivityUtil.constructObjectUsingOneParam(ProjectStateSpecification.class, criteria);
        return mySpecification;
    }

    public List<ProjectState> findPaginatedByCriteria(ProjectStateCriteria criteria, int page, int pageSize, String order, String sortField) {
        ProjectStateSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ProjectStateCriteria criteria) {
        ProjectStateSpecification mySpecification = constructSpecification(criteria);
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
    public List<ProjectState> delete(List<ProjectState> list) {
		List<ProjectState> result = new ArrayList();
        if (list != null) {
            for (ProjectState t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProjectState create(ProjectState t) {
        ProjectState loaded = findByReferenceEntity(t);
        ProjectState saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ProjectState findWithAssociatedLists(Long id){
        ProjectState result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ProjectState> update(List<ProjectState> ts, boolean createIfNotExist) {
        List<ProjectState> result = new ArrayList<>();
        if (ts != null) {
            for (ProjectState t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    ProjectState loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ProjectState t, ProjectState loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ProjectState findByReferenceEntity(ProjectState t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<ProjectState> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<ProjectState>> getToBeSavedAndToBeDeleted(List<ProjectState> oldList, List<ProjectState> newList) {
        List<List<ProjectState>> result = new ArrayList<>();
        List<ProjectState> resultDelete = new ArrayList<>();
        List<ProjectState> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ProjectState> oldList, List<ProjectState> newList, List<ProjectState> resultUpdateOrSave, List<ProjectState> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ProjectState myOld = oldList.get(i);
                ProjectState t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ProjectState myNew = newList.get(i);
                ProjectState t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public ProjectStateAdminServiceImpl(ProjectStateDao dao) {
        this.dao = dao;
    }

    private ProjectStateDao dao;
}
