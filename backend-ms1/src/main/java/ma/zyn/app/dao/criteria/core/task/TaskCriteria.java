package  ma.zyn.app.dao.criteria.core.task;


import ma.zyn.app.dao.criteria.core.project.ProjectCriteria;
import ma.zyn.app.dao.criteria.core.member.MemberCriteria;

import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class TaskCriteria extends  BaseCriteria  {

    private String name;
    private String nameLike;
    private String description;
    private String descriptionLike;
    private LocalDateTime startDate;
    private LocalDateTime startDateFrom;
    private LocalDateTime startDateTo;
    private LocalDateTime endDate;
    private LocalDateTime endDateFrom;
    private LocalDateTime endDateTo;

    private TaskStateCriteria taskState ;
    private List<TaskStateCriteria> taskStates ;
    private MemberCriteria assignedTo ;
    private List<MemberCriteria> assignedTos ;
    private ProjectCriteria project ;
    private List<ProjectCriteria> projects ;


    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getNameLike(){
        return this.nameLike;
    }
    public void setNameLike(String nameLike){
        this.nameLike = nameLike;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescriptionLike(){
        return this.descriptionLike;
    }
    public void setDescriptionLike(String descriptionLike){
        this.descriptionLike = descriptionLike;
    }

    public LocalDateTime getStartDate(){
        return this.startDate;
    }
    public void setStartDate(LocalDateTime startDate){
        this.startDate = startDate;
    }
    public LocalDateTime getStartDateFrom(){
        return this.startDateFrom;
    }
    public void setStartDateFrom(LocalDateTime startDateFrom){
        this.startDateFrom = startDateFrom;
    }
    public LocalDateTime getStartDateTo(){
        return this.startDateTo;
    }
    public void setStartDateTo(LocalDateTime startDateTo){
        this.startDateTo = startDateTo;
    }
    public LocalDateTime getEndDate(){
        return this.endDate;
    }
    public void setEndDate(LocalDateTime endDate){
        this.endDate = endDate;
    }
    public LocalDateTime getEndDateFrom(){
        return this.endDateFrom;
    }
    public void setEndDateFrom(LocalDateTime endDateFrom){
        this.endDateFrom = endDateFrom;
    }
    public LocalDateTime getEndDateTo(){
        return this.endDateTo;
    }
    public void setEndDateTo(LocalDateTime endDateTo){
        this.endDateTo = endDateTo;
    }

    public TaskStateCriteria getTaskState(){
        return this.taskState;
    }

    public void setTaskState(TaskStateCriteria taskState){
        this.taskState = taskState;
    }
    public List<TaskStateCriteria> getTaskStates(){
        return this.taskStates;
    }

    public void setTaskStates(List<TaskStateCriteria> taskStates){
        this.taskStates = taskStates;
    }
    public MemberCriteria getAssignedTo(){
        return this.assignedTo;
    }

    public void setAssignedTo(MemberCriteria assignedTo){
        this.assignedTo = assignedTo;
    }
    public List<MemberCriteria> getAssignedTos(){
        return this.assignedTos;
    }

    public void setAssignedTos(List<MemberCriteria> assignedTos){
        this.assignedTos = assignedTos;
    }
    public ProjectCriteria getProject(){
        return this.project;
    }

    public void setProject(ProjectCriteria project){
        this.project = project;
    }
    public List<ProjectCriteria> getProjects(){
        return this.projects;
    }

    public void setProjects(List<ProjectCriteria> projects){
        this.projects = projects;
    }
}
