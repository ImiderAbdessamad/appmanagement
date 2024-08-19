package ma.zyn.app.service.facade.admin.project;

import java.util.List;
import ma.zyn.app.bean.core.project.ProjectCategory;
import ma.zyn.app.dao.criteria.core.project.ProjectCategoryCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface ProjectCategoryAdminService {







	ProjectCategory create(ProjectCategory t);

    ProjectCategory update(ProjectCategory t);

    List<ProjectCategory> update(List<ProjectCategory> ts,boolean createIfNotExist);

    ProjectCategory findById(Long id);

    ProjectCategory findOrSave(ProjectCategory t);

    ProjectCategory findByReferenceEntity(ProjectCategory t);

    ProjectCategory findWithAssociatedLists(Long id);

    List<ProjectCategory> findAllOptimized();

    List<ProjectCategory> findAll();

    List<ProjectCategory> findByCriteria(ProjectCategoryCriteria criteria);

    List<ProjectCategory> findPaginatedByCriteria(ProjectCategoryCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ProjectCategoryCriteria criteria);

    List<ProjectCategory> delete(List<ProjectCategory> ts);

    boolean deleteById(Long id);

    List<List<ProjectCategory>> getToBeSavedAndToBeDeleted(List<ProjectCategory> oldList, List<ProjectCategory> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
