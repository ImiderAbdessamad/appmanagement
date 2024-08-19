package ma.zyn.app.service.impl.admin.member;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.member.Member;
import ma.zyn.app.dao.criteria.core.member.MemberCriteria;
import ma.zyn.app.dao.facade.core.member.MemberDao;
import ma.zyn.app.dao.specification.core.member.MemberSpecification;
import ma.zyn.app.service.facade.admin.member.MemberAdminService;
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
import ma.zyn.app.service.facade.admin.role.RoleAdminService ;
import ma.zyn.app.bean.core.role.Role ;

import java.util.List;
@Service
public class MemberAdminServiceImpl implements MemberAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Member update(Member t) {
        Member loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Member.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public Member findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Member findOrSave(Member t) {
        if (t != null) {
            Member result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Member> findAll() {
        return dao.findAll();
    }

    public List<Member> findByCriteria(MemberCriteria criteria) {
        List<Member> content = null;
        if (criteria != null) {
            MemberSpecification mySpecification = constructSpecification(criteria);
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


    private MemberSpecification constructSpecification(MemberCriteria criteria) {
        MemberSpecification mySpecification =  (MemberSpecification) RefelexivityUtil.constructObjectUsingOneParam(MemberSpecification.class, criteria);
        return mySpecification;
    }

    public List<Member> findPaginatedByCriteria(MemberCriteria criteria, int page, int pageSize, String order, String sortField) {
        MemberSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(MemberCriteria criteria) {
        MemberSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
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
        roleService.deleteByMemberId(id);
        projectTeamService.deleteByMemberId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Member> delete(List<Member> list) {
		List<Member> result = new ArrayList();
        if (list != null) {
            for (Member t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Member create(Member t) {
        Member loaded = findByReferenceEntity(t);
        Member saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getRoles() != null) {
                t.getRoles().forEach(element-> {
                    element.setMember(saved);
                    roleService.create(element);
                });
            }
            if (t.getProjectTeams() != null) {
                t.getProjectTeams().forEach(element-> {
                    element.setMember(saved);
                    projectTeamService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

    public Member findWithAssociatedLists(Long id){
        Member result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setRoles(roleService.findByMemberId(id));
            result.setProjectTeams(projectTeamService.findByMemberId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Member> update(List<Member> ts, boolean createIfNotExist) {
        List<Member> result = new ArrayList<>();
        if (ts != null) {
            for (Member t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Member loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Member t, Member loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(Member member){
    if(member !=null && member.getId() != null){
        List<List<Role>> resultRoles= roleService.getToBeSavedAndToBeDeleted(roleService.findByMemberId(member.getId()),member.getRoles());
            roleService.delete(resultRoles.get(1));
        emptyIfNull(resultRoles.get(0)).forEach(e -> e.setMember(member));
        roleService.update(resultRoles.get(0),true);
        List<List<ProjectTeam>> resultProjectTeams= projectTeamService.getToBeSavedAndToBeDeleted(projectTeamService.findByMemberId(member.getId()),member.getProjectTeams());
            projectTeamService.delete(resultProjectTeams.get(1));
        emptyIfNull(resultProjectTeams.get(0)).forEach(e -> e.setMember(member));
        projectTeamService.update(resultProjectTeams.get(0),true);
        }
    }








    public Member findByReferenceEntity(Member t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }



    public List<Member> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Member>> getToBeSavedAndToBeDeleted(List<Member> oldList, List<Member> newList) {
        List<List<Member>> result = new ArrayList<>();
        List<Member> resultDelete = new ArrayList<>();
        List<Member> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Member> oldList, List<Member> newList, List<Member> resultUpdateOrSave, List<Member> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Member myOld = oldList.get(i);
                Member t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Member myNew = newList.get(i);
                Member t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private RoleAdminService roleService ;

    public MemberAdminServiceImpl(MemberDao dao) {
        this.dao = dao;
    }

    private MemberDao dao;
}
