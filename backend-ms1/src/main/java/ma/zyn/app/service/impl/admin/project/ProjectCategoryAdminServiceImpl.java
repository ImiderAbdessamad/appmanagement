package ma.zyn.app.service.impl.admin.project;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.project.ProjectCategory;
import ma.zyn.app.dao.criteria.core.project.ProjectCategoryCriteria;
import ma.zyn.app.dao.facade.core.project.ProjectCategoryDao;
import ma.zyn.app.dao.specification.core.project.ProjectCategorySpecification;
import ma.zyn.app.service.facade.admin.project.ProjectCategoryAdminService;
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
public class ProjectCategoryAdminServiceImpl implements ProjectCategoryAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProjectCategory update(ProjectCategory t) {
        ProjectCategory loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{ProjectCategory.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public ProjectCategory findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public ProjectCategory findOrSave(ProjectCategory t) {
        if (t != null) {
            ProjectCategory result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ProjectCategory> findAll() {
        return dao.findAll();
    }

    public List<ProjectCategory> findByCriteria(ProjectCategoryCriteria criteria) {
        List<ProjectCategory> content = null;
        if (criteria != null) {
            ProjectCategorySpecification mySpecification = constructSpecification(criteria);
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


    private ProjectCategorySpecification constructSpecification(ProjectCategoryCriteria criteria) {
        ProjectCategorySpecification mySpecification =  (ProjectCategorySpecification) RefelexivityUtil.constructObjectUsingOneParam(ProjectCategorySpecification.class, criteria);
        return mySpecification;
    }

    public List<ProjectCategory> findPaginatedByCriteria(ProjectCategoryCriteria criteria, int page, int pageSize, String order, String sortField) {
        ProjectCategorySpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ProjectCategoryCriteria criteria) {
        ProjectCategorySpecification mySpecification = constructSpecification(criteria);
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
    public List<ProjectCategory> delete(List<ProjectCategory> list) {
		List<ProjectCategory> result = new ArrayList();
        if (list != null) {
            for (ProjectCategory t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProjectCategory create(ProjectCategory t) {
        ProjectCategory loaded = findByReferenceEntity(t);
        ProjectCategory saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ProjectCategory findWithAssociatedLists(Long id){
        ProjectCategory result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ProjectCategory> update(List<ProjectCategory> ts, boolean createIfNotExist) {
        List<ProjectCategory> result = new ArrayList<>();
        if (ts != null) {
            for (ProjectCategory t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    ProjectCategory loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ProjectCategory t, ProjectCategory loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ProjectCategory findByReferenceEntity(ProjectCategory t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<ProjectCategory> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<ProjectCategory>> getToBeSavedAndToBeDeleted(List<ProjectCategory> oldList, List<ProjectCategory> newList) {
        List<List<ProjectCategory>> result = new ArrayList<>();
        List<ProjectCategory> resultDelete = new ArrayList<>();
        List<ProjectCategory> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ProjectCategory> oldList, List<ProjectCategory> newList, List<ProjectCategory> resultUpdateOrSave, List<ProjectCategory> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ProjectCategory myOld = oldList.get(i);
                ProjectCategory t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ProjectCategory myNew = newList.get(i);
                ProjectCategory t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public ProjectCategoryAdminServiceImpl(ProjectCategoryDao dao) {
        this.dao = dao;
    }

    private ProjectCategoryDao dao;
}
