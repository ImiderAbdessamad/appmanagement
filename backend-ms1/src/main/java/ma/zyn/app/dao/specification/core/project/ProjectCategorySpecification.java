package  ma.zyn.app.dao.specification.core.project;

import ma.zyn.app.dao.criteria.core.project.ProjectCategoryCriteria;
import ma.zyn.app.bean.core.project.ProjectCategory;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class ProjectCategorySpecification extends  AbstractSpecification<ProjectCategoryCriteria, ProjectCategory>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public ProjectCategorySpecification(ProjectCategoryCriteria criteria) {
        super(criteria);
    }

    public ProjectCategorySpecification(ProjectCategoryCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
