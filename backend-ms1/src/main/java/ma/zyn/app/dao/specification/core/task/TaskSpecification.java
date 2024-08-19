package  ma.zyn.app.dao.specification.core.task;

import ma.zyn.app.dao.criteria.core.task.TaskCriteria;
import ma.zyn.app.bean.core.task.Task;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class TaskSpecification extends  AbstractSpecification<TaskCriteria, Task>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("name", criteria.getName(),criteria.getNameLike());
        addPredicate("startDate", criteria.getStartDate(), criteria.getStartDateFrom(), criteria.getStartDateTo());
        addPredicate("endDate", criteria.getEndDate(), criteria.getEndDateFrom(), criteria.getEndDateTo());
        addPredicateFk("taskState","id", criteria.getTaskState()==null?null:criteria.getTaskState().getId());
        addPredicateFk("taskState","id", criteria.getTaskStates());
        addPredicateFk("taskState","code", criteria.getTaskState()==null?null:criteria.getTaskState().getCode());
        addPredicateFk("assignedTo","id", criteria.getAssignedTo()==null?null:criteria.getAssignedTo().getId());
        addPredicateFk("assignedTo","id", criteria.getAssignedTos());
        addPredicateFk("project","id", criteria.getProject()==null?null:criteria.getProject().getId());
        addPredicateFk("project","id", criteria.getProjects());
        addPredicateFk("project","code", criteria.getProject()==null?null:criteria.getProject().getCode());
    }

    public TaskSpecification(TaskCriteria criteria) {
        super(criteria);
    }

    public TaskSpecification(TaskCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
