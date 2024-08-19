package ma.zyn.app.service.impl.admin.project;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.project.Project;
import ma.zyn.app.dao.criteria.core.project.ProjectCriteria;
import ma.zyn.app.dao.facade.core.project.ProjectDao;
import ma.zyn.app.dao.specification.core.project.ProjectSpecification;
import ma.zyn.app.service.facade.admin.project.ProjectAdminService;
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

import ma.zyn.app.service.facade.admin.project.ProjectTeamAdminService ;
import ma.zyn.app.bean.core.project.ProjectTeam ;
import ma.zyn.app.service.facade.admin.project.ProjectStateAdminService ;
import ma.zyn.app.bean.core.project.ProjectState ;
import ma.zyn.app.service.facade.admin.project.ProjectCategoryAdminService ;
import ma.zyn.app.bean.core.project.ProjectCategory ;
import ma.zyn.app.service.facade.admin.project.ProjectTypeAdminService ;
import ma.zyn.app.bean.core.project.ProjectType ;

import java.util.List;
@Service
public class ProjectAdminServiceImpl implements ProjectAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Project update(Project t) {
        Project loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Project.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Project findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Project findOrSave(Project t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Project result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Project> findAll() {
        return dao.findAll();
    }

    public List<Project> findByCriteria(ProjectCriteria criteria) {
        List<Project> content = null;
        if (criteria != null) {
            ProjectSpecification mySpecification = constructSpecification(criteria);
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


    private ProjectSpecification constructSpecification(ProjectCriteria criteria) {
        ProjectSpecification mySpecification =  (ProjectSpecification) RefelexivityUtil.constructObjectUsingOneParam(ProjectSpecification.class, criteria);
        return mySpecification;
    }

    public List<Project> findPaginatedByCriteria(ProjectCriteria criteria, int page, int pageSize, String order, String sortField) {
        ProjectSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ProjectCriteria criteria) {
        ProjectSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Project> findByProjectStateCode(String code){
        return dao.findByProjectStateCode(code);
    }
    public int deleteByProjectStateCode(String code){
        return dao.deleteByProjectStateCode(code);
    }
    public long countByProjectStateCode(String code){
        return dao.countByProjectStateCode(code);
    }
    public List<Project> findByProjectTeamId(Long id){
        return dao.findByProjectTeamId(id);
    }
    public int deleteByProjectTeamId(Long id){
        return dao.deleteByProjectTeamId(id);
    }
    public long countByProjectTeamId(Long id){
        return dao.countByProjectTeamId(id);
    }
    public List<Project> findByProjectTypeId(Long id){
        return dao.findByProjectTypeId(id);
    }
    public int deleteByProjectTypeId(Long id){
        return dao.deleteByProjectTypeId(id);
    }
    public long countByProjectTypeCode(String code){
        return dao.countByProjectTypeCode(code);
    }
    public List<Project> findByProjectCategoryId(Long id){
        return dao.findByProjectCategoryId(id);
    }
    public int deleteByProjectCategoryId(Long id){
        return dao.deleteByProjectCategoryId(id);
    }
    public long countByProjectCategoryCode(String code){
        return dao.countByProjectCategoryCode(code);
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
    public List<Project> delete(List<Project> list) {
		List<Project> result = new ArrayList();
        if (list != null) {
            for (Project t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Project create(Project t) {
        Project loaded = findByReferenceEntity(t);
        Project saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Project findWithAssociatedLists(Long id){
        Project result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Project> update(List<Project> ts, boolean createIfNotExist) {
        List<Project> result = new ArrayList<>();
        if (ts != null) {
            for (Project t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Project loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Project t, Project loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Project findByReferenceEntity(Project t){
        return t==null? null : dao.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(Project t){
        if( t != null) {
            t.setProjectState(projectStateService.findOrSave(t.getProjectState()));
            t.setProjectTeam(projectTeamService.findOrSave(t.getProjectTeam()));
            t.setProjectType(projectTypeService.findOrSave(t.getProjectType()));
            t.setProjectCategory(projectCategoryService.findOrSave(t.getProjectCategory()));
        }
    }



    public List<Project> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Project>> getToBeSavedAndToBeDeleted(List<Project> oldList, List<Project> newList) {
        List<List<Project>> result = new ArrayList<>();
        List<Project> resultDelete = new ArrayList<>();
        List<Project> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Project> oldList, List<Project> newList, List<Project> resultUpdateOrSave, List<Project> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Project myOld = oldList.get(i);
                Project t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Project myNew = newList.get(i);
                Project t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private ProjectTeamAdminService projectTeamService ;
    @Autowired
    private ProjectStateAdminService projectStateService ;
    @Autowired
    private ProjectCategoryAdminService projectCategoryService ;
    @Autowired
    private ProjectTypeAdminService projectTypeService ;

    public ProjectAdminServiceImpl(ProjectDao dao) {
        this.dao = dao;
    }

    private ProjectDao dao;
}
