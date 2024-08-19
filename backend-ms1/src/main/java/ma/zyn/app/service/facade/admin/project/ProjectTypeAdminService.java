package ma.zyn.app.service.facade.admin.project;

import java.util.List;
import ma.zyn.app.bean.core.project.ProjectType;
import ma.zyn.app.dao.criteria.core.project.ProjectTypeCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface ProjectTypeAdminService {







	ProjectType create(ProjectType t);

    ProjectType update(ProjectType t);

    List<ProjectType> update(List<ProjectType> ts,boolean createIfNotExist);

    ProjectType findById(Long id);

    ProjectType findOrSave(ProjectType t);

    ProjectType findByReferenceEntity(ProjectType t);

    ProjectType findWithAssociatedLists(Long id);

    List<ProjectType> findAllOptimized();

    List<ProjectType> findAll();

    List<ProjectType> findByCriteria(ProjectTypeCriteria criteria);

    List<ProjectType> findPaginatedByCriteria(ProjectTypeCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ProjectTypeCriteria criteria);

    List<ProjectType> delete(List<ProjectType> ts);

    boolean deleteById(Long id);

    List<List<ProjectType>> getToBeSavedAndToBeDeleted(List<ProjectType> oldList, List<ProjectType> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
