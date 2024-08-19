package ma.zyn.app.service.impl.admin.project;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.project.ProjectType;
import ma.zyn.app.dao.criteria.core.project.ProjectTypeCriteria;
import ma.zyn.app.dao.facade.core.project.ProjectTypeDao;
import ma.zyn.app.dao.specification.core.project.ProjectTypeSpecification;
import ma.zyn.app.service.facade.admin.project.ProjectTypeAdminService;
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
public class ProjectTypeAdminServiceImpl implements ProjectTypeAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProjectType update(ProjectType t) {
        ProjectType loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{ProjectType.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public ProjectType findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public ProjectType findOrSave(ProjectType t) {
        if (t != null) {
            ProjectType result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ProjectType> findAll() {
        return dao.findAll();
    }

    public List<ProjectType> findByCriteria(ProjectTypeCriteria criteria) {
        List<ProjectType> content = null;
        if (criteria != null) {
            ProjectTypeSpecification mySpecification = constructSpecification(criteria);
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


    private ProjectTypeSpecification constructSpecification(ProjectTypeCriteria criteria) {
        ProjectTypeSpecification mySpecification =  (ProjectTypeSpecification) RefelexivityUtil.constructObjectUsingOneParam(ProjectTypeSpecification.class, criteria);
        return mySpecification;
    }

    public List<ProjectType> findPaginatedByCriteria(ProjectTypeCriteria criteria, int page, int pageSize, String order, String sortField) {
        ProjectTypeSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ProjectTypeCriteria criteria) {
        ProjectTypeSpecification mySpecification = constructSpecification(criteria);
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
    public List<ProjectType> delete(List<ProjectType> list) {
		List<ProjectType> result = new ArrayList();
        if (list != null) {
            for (ProjectType t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProjectType create(ProjectType t) {
        ProjectType loaded = findByReferenceEntity(t);
        ProjectType saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ProjectType findWithAssociatedLists(Long id){
        ProjectType result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ProjectType> update(List<ProjectType> ts, boolean createIfNotExist) {
        List<ProjectType> result = new ArrayList<>();
        if (ts != null) {
            for (ProjectType t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    ProjectType loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ProjectType t, ProjectType loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ProjectType findByReferenceEntity(ProjectType t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<ProjectType> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<ProjectType>> getToBeSavedAndToBeDeleted(List<ProjectType> oldList, List<ProjectType> newList) {
        List<List<ProjectType>> result = new ArrayList<>();
        List<ProjectType> resultDelete = new ArrayList<>();
        List<ProjectType> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ProjectType> oldList, List<ProjectType> newList, List<ProjectType> resultUpdateOrSave, List<ProjectType> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ProjectType myOld = oldList.get(i);
                ProjectType t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ProjectType myNew = newList.get(i);
                ProjectType t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public ProjectTypeAdminServiceImpl(ProjectTypeDao dao) {
        this.dao = dao;
    }

    private ProjectTypeDao dao;
}
