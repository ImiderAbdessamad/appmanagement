package  ma.zyn.app.dao.specification.core.project;

import ma.zyn.app.dao.criteria.core.project.ProjectStateCriteria;
import ma.zyn.app.bean.core.project.ProjectState;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class ProjectStateSpecification extends  AbstractSpecification<ProjectStateCriteria, ProjectState>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public ProjectStateSpecification(ProjectStateCriteria criteria) {
        super(criteria);
    }

    public ProjectStateSpecification(ProjectStateCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
