package ma.zyn.app.service.facade.admin.project;

import java.util.List;
import ma.zyn.app.bean.core.project.ProjectState;
import ma.zyn.app.dao.criteria.core.project.ProjectStateCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface ProjectStateAdminService {







	ProjectState create(ProjectState t);

    ProjectState update(ProjectState t);

    List<ProjectState> update(List<ProjectState> ts,boolean createIfNotExist);

    ProjectState findById(Long id);

    ProjectState findOrSave(ProjectState t);

    ProjectState findByReferenceEntity(ProjectState t);

    ProjectState findWithAssociatedLists(Long id);

    List<ProjectState> findAllOptimized();

    List<ProjectState> findAll();

    List<ProjectState> findByCriteria(ProjectStateCriteria criteria);

    List<ProjectState> findPaginatedByCriteria(ProjectStateCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ProjectStateCriteria criteria);

    List<ProjectState> delete(List<ProjectState> ts);

    boolean deleteById(Long id);

    List<List<ProjectState>> getToBeSavedAndToBeDeleted(List<ProjectState> oldList, List<ProjectState> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
