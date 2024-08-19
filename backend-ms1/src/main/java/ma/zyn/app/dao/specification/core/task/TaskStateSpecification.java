package  ma.zyn.app.dao.specification.core.task;

import ma.zyn.app.dao.criteria.core.task.TaskStateCriteria;
import ma.zyn.app.bean.core.task.TaskState;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class TaskStateSpecification extends  AbstractSpecification<TaskStateCriteria, TaskState>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public TaskStateSpecification(TaskStateCriteria criteria) {
        super(criteria);
    }

    public TaskStateSpecification(TaskStateCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
