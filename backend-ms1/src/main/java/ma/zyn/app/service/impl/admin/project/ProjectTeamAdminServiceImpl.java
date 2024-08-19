package ma.zyn.app.service.impl.admin.project;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.project.ProjectTeam;
import ma.zyn.app.dao.criteria.core.project.ProjectTeamCriteria;
import ma.zyn.app.dao.facade.core.project.ProjectTeamDao;
import ma.zyn.app.dao.specification.core.project.ProjectTeamSpecification;
import ma.zyn.app.service.facade.admin.project.ProjectTeamAdminService;
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
import ma.zyn.app.service.facade.admin.member.MemberAdminService ;
import ma.zyn.app.bean.core.member.Member ;

import java.util.List;
@Service
public class ProjectTeamAdminServiceImpl implements ProjectTeamAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProjectTeam update(ProjectTeam t) {
        ProjectTeam loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{ProjectTeam.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public ProjectTeam findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public ProjectTeam findOrSave(ProjectTeam t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            ProjectTeam result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ProjectTeam> findAll() {
        return dao.findAll();
    }

    public List<ProjectTeam> findByCriteria(ProjectTeamCriteria criteria) {
        List<ProjectTeam> content = null;
        if (criteria != null) {
            ProjectTeamSpecification mySpecification = constructSpecification(criteria);
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


    private ProjectTeamSpecification constructSpecification(ProjectTeamCriteria criteria) {
        ProjectTeamSpecification mySpecification =  (ProjectTeamSpecification) RefelexivityUtil.constructObjectUsingOneParam(ProjectTeamSpecification.class, criteria);
        return mySpecification;
    }

    public List<ProjectTeam> findPaginatedByCriteria(ProjectTeamCriteria criteria, int page, int pageSize, String order, String sortField) {
        ProjectTeamSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ProjectTeamCriteria criteria) {
        ProjectTeamSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<ProjectTeam> findByProjectId(Long id){
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
        memberService.deleteByProjectTeamId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ProjectTeam> delete(List<ProjectTeam> list) {
		List<ProjectTeam> result = new ArrayList();
        if (list != null) {
            for (ProjectTeam t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProjectTeam create(ProjectTeam t) {
        ProjectTeam loaded = findByReferenceEntity(t);
        ProjectTeam saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getMembers() != null) {
                t.getMembers().forEach(element-> {
                    element.setProjectTeam(saved);
                    memberService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

    public ProjectTeam findWithAssociatedLists(Long id){
        ProjectTeam result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setMembers(memberService.findByProjectTeamId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ProjectTeam> update(List<ProjectTeam> ts, boolean createIfNotExist) {
        List<ProjectTeam> result = new ArrayList<>();
        if (ts != null) {
            for (ProjectTeam t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    ProjectTeam loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ProjectTeam t, ProjectTeam loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(ProjectTeam projectTeam){
    if(projectTeam !=null && projectTeam.getId() != null){
        List<List<Member>> resultMembers= memberService.getToBeSavedAndToBeDeleted(memberService.findByProjectTeamId(projectTeam.getId()),projectTeam.getMembers());
            memberService.delete(resultMembers.get(1));
        emptyIfNull(resultMembers.get(0)).forEach(e -> e.setProjectTeam(projectTeam));
        memberService.update(resultMembers.get(0),true);
        }
    }








    public ProjectTeam findByReferenceEntity(ProjectTeam t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(ProjectTeam t){
        if( t != null) {
            t.setProject(projectService.findOrSave(t.getProject()));
        }
    }



    public List<ProjectTeam> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<ProjectTeam>> getToBeSavedAndToBeDeleted(List<ProjectTeam> oldList, List<ProjectTeam> newList) {
        List<List<ProjectTeam>> result = new ArrayList<>();
        List<ProjectTeam> resultDelete = new ArrayList<>();
        List<ProjectTeam> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ProjectTeam> oldList, List<ProjectTeam> newList, List<ProjectTeam> resultUpdateOrSave, List<ProjectTeam> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ProjectTeam myOld = oldList.get(i);
                ProjectTeam t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ProjectTeam myNew = newList.get(i);
                ProjectTeam t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private MemberAdminService memberService ;

    public ProjectTeamAdminServiceImpl(ProjectTeamDao dao) {
        this.dao = dao;
    }

    private ProjectTeamDao dao;
}
