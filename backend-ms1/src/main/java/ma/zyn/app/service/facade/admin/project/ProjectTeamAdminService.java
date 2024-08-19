package ma.zyn.app.service.facade.admin.project;

import java.util.List;
import ma.zyn.app.bean.core.project.ProjectTeam;
import ma.zyn.app.dao.criteria.core.project.ProjectTeamCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface ProjectTeamAdminService {



    List<ProjectTeam> findByProjectId(Long id);
    int deleteByProjectId(Long id);
    long countByProjectCode(String code);




	ProjectTeam create(ProjectTeam t);

    ProjectTeam update(ProjectTeam t);

    List<ProjectTeam> update(List<ProjectTeam> ts,boolean createIfNotExist);

    ProjectTeam findById(Long id);

    ProjectTeam findOrSave(ProjectTeam t);

    ProjectTeam findByReferenceEntity(ProjectTeam t);

    ProjectTeam findWithAssociatedLists(Long id);

    List<ProjectTeam> findAllOptimized();

    List<ProjectTeam> findAll();

    List<ProjectTeam> findByCriteria(ProjectTeamCriteria criteria);

    List<ProjectTeam> findPaginatedByCriteria(ProjectTeamCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ProjectTeamCriteria criteria);

    List<ProjectTeam> delete(List<ProjectTeam> ts);

    boolean deleteById(Long id);

    List<List<ProjectTeam>> getToBeSavedAndToBeDeleted(List<ProjectTeam> oldList, List<ProjectTeam> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
