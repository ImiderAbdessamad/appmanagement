package  ma.zyn.app.dao.specification.core.project;

import ma.zyn.app.dao.criteria.core.project.ProjectCriteria;
import ma.zyn.app.bean.core.project.Project;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class ProjectSpecification extends  AbstractSpecification<ProjectCriteria, Project>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("name", criteria.getName(),criteria.getNameLike());
        addPredicate("startDate", criteria.getStartDate(), criteria.getStartDateFrom(), criteria.getStartDateTo());
        addPredicate("endDate", criteria.getEndDate(), criteria.getEndDateFrom(), criteria.getEndDateTo());
        addPredicateFk("projectState","id", criteria.getProjectState()==null?null:criteria.getProjectState().getId());
        addPredicateFk("projectState","id", criteria.getProjectStates());
        addPredicateFk("projectState","code", criteria.getProjectState()==null?null:criteria.getProjectState().getCode());
        addPredicateFk("projectTeam","id", criteria.getProjectTeam()==null?null:criteria.getProjectTeam().getId());
        addPredicateFk("projectTeam","id", criteria.getProjectTeams());
        addPredicateFk("projectType","id", criteria.getProjectType()==null?null:criteria.getProjectType().getId());
        addPredicateFk("projectType","id", criteria.getProjectTypes());
        addPredicateFk("projectType","code", criteria.getProjectType()==null?null:criteria.getProjectType().getCode());
        addPredicateFk("projectCategory","id", criteria.getProjectCategory()==null?null:criteria.getProjectCategory().getId());
        addPredicateFk("projectCategory","id", criteria.getProjectCategorys());
        addPredicateFk("projectCategory","code", criteria.getProjectCategory()==null?null:criteria.getProjectCategory().getCode());
    }

    public ProjectSpecification(ProjectCriteria criteria) {
        super(criteria);
    }

    public ProjectSpecification(ProjectCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
