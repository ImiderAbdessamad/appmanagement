package ma.zyn.app.service.facade.admin.role;

import java.util.List;
import ma.zyn.app.bean.core.role.Role;
import ma.zyn.app.dao.criteria.core.role.RoleCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface RoleAdminService {







	Role create(Role t);

    Role update(Role t);

    List<Role> update(List<Role> ts,boolean createIfNotExist);

    Role findById(Long id);

    Role findOrSave(Role t);

    Role findByReferenceEntity(Role t);

    Role findWithAssociatedLists(Long id);

    List<Role> findAllOptimized();

    List<Role> findAll();

    List<Role> findByCriteria(RoleCriteria criteria);

    List<Role> findPaginatedByCriteria(RoleCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(RoleCriteria criteria);

    List<Role> delete(List<Role> ts);

    boolean deleteById(Long id);

    List<List<Role>> getToBeSavedAndToBeDeleted(List<Role> oldList, List<Role> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
