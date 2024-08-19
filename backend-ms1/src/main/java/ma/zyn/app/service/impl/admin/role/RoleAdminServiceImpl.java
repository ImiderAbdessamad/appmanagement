package ma.zyn.app.service.impl.admin.role;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.role.Role;
import ma.zyn.app.dao.criteria.core.role.RoleCriteria;
import ma.zyn.app.dao.facade.core.role.RoleDao;
import ma.zyn.app.dao.specification.core.role.RoleSpecification;
import ma.zyn.app.service.facade.admin.role.RoleAdminService;
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
public class RoleAdminServiceImpl implements RoleAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Role update(Role t) {
        Role loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Role.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Role findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Role findOrSave(Role t) {
        if (t != null) {
            Role result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Role> findAll() {
        return dao.findAll();
    }

    public List<Role> findByCriteria(RoleCriteria criteria) {
        List<Role> content = null;
        if (criteria != null) {
            RoleSpecification mySpecification = constructSpecification(criteria);
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


    private RoleSpecification constructSpecification(RoleCriteria criteria) {
        RoleSpecification mySpecification =  (RoleSpecification) RefelexivityUtil.constructObjectUsingOneParam(RoleSpecification.class, criteria);
        return mySpecification;
    }

    public List<Role> findPaginatedByCriteria(RoleCriteria criteria, int page, int pageSize, String order, String sortField) {
        RoleSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(RoleCriteria criteria) {
        RoleSpecification mySpecification = constructSpecification(criteria);
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
    public List<Role> delete(List<Role> list) {
		List<Role> result = new ArrayList();
        if (list != null) {
            for (Role t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Role create(Role t) {
        Role loaded = findByReferenceEntity(t);
        Role saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Role findWithAssociatedLists(Long id){
        Role result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Role> update(List<Role> ts, boolean createIfNotExist) {
        List<Role> result = new ArrayList<>();
        if (ts != null) {
            for (Role t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Role loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Role t, Role loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Role findByReferenceEntity(Role t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<Role> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Role>> getToBeSavedAndToBeDeleted(List<Role> oldList, List<Role> newList) {
        List<List<Role>> result = new ArrayList<>();
        List<Role> resultDelete = new ArrayList<>();
        List<Role> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Role> oldList, List<Role> newList, List<Role> resultUpdateOrSave, List<Role> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Role myOld = oldList.get(i);
                Role t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Role myNew = newList.get(i);
                Role t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public RoleAdminServiceImpl(RoleDao dao) {
        this.dao = dao;
    }

    private RoleDao dao;
}
