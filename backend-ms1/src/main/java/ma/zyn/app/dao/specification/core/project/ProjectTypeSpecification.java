package  ma.zyn.app.dao.specification.core.project;

import ma.zyn.app.dao.criteria.core.project.ProjectTypeCriteria;
import ma.zyn.app.bean.core.project.ProjectType;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class ProjectTypeSpecification extends  AbstractSpecification<ProjectTypeCriteria, ProjectType>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public ProjectTypeSpecification(ProjectTypeCriteria criteria) {
        super(criteria);
    }

    public ProjectTypeSpecification(ProjectTypeCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
