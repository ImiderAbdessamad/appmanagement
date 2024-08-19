package  ma.zyn.app.dao.specification.core.project;

import ma.zyn.app.dao.criteria.core.project.ProjectTeamCriteria;
import ma.zyn.app.bean.core.project.ProjectTeam;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class ProjectTeamSpecification extends  AbstractSpecification<ProjectTeamCriteria, ProjectTeam>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("name", criteria.getName(),criteria.getNameLike());
        addPredicateFk("project","id", criteria.getProject()==null?null:criteria.getProject().getId());
        addPredicateFk("project","id", criteria.getProjects());
        addPredicateFk("project","code", criteria.getProject()==null?null:criteria.getProject().getCode());
    }

    public ProjectTeamSpecification(ProjectTeamCriteria criteria) {
        super(criteria);
    }

    public ProjectTeamSpecification(ProjectTeamCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
