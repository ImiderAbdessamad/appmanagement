package  ma.zyn.app.dao.criteria.core.project;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class ProjectCriteria extends  BaseCriteria  {

    private String code;
    private String codeLike;
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

    private ProjectStateCriteria projectState ;
    private List<ProjectStateCriteria> projectStates ;
    private ProjectTeamCriteria projectTeam ;
    private List<ProjectTeamCriteria> projectTeams ;
    private ProjectTypeCriteria projectType ;
    private List<ProjectTypeCriteria> projectTypes ;
    private ProjectCategoryCriteria projectCategory ;
    private List<ProjectCategoryCriteria> projectCategorys ;


    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCodeLike(){
        return this.codeLike;
    }
    public void setCodeLike(String codeLike){
        this.codeLike = codeLike;
    }

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

    public ProjectStateCriteria getProjectState(){
        return this.projectState;
    }

    public void setProjectState(ProjectStateCriteria projectState){
        this.projectState = projectState;
    }
    public List<ProjectStateCriteria> getProjectStates(){
        return this.projectStates;
    }

    public void setProjectStates(List<ProjectStateCriteria> projectStates){
        this.projectStates = projectStates;
    }
    public ProjectTeamCriteria getProjectTeam(){
        return this.projectTeam;
    }

    public void setProjectTeam(ProjectTeamCriteria projectTeam){
        this.projectTeam = projectTeam;
    }
    public List<ProjectTeamCriteria> getProjectTeams(){
        return this.projectTeams;
    }

    public void setProjectTeams(List<ProjectTeamCriteria> projectTeams){
        this.projectTeams = projectTeams;
    }
    public ProjectTypeCriteria getProjectType(){
        return this.projectType;
    }

    public void setProjectType(ProjectTypeCriteria projectType){
        this.projectType = projectType;
    }
    public List<ProjectTypeCriteria> getProjectTypes(){
        return this.projectTypes;
    }

    public void setProjectTypes(List<ProjectTypeCriteria> projectTypes){
        this.projectTypes = projectTypes;
    }
    public ProjectCategoryCriteria getProjectCategory(){
        return this.projectCategory;
    }

    public void setProjectCategory(ProjectCategoryCriteria projectCategory){
        this.projectCategory = projectCategory;
    }
    public List<ProjectCategoryCriteria> getProjectCategorys(){
        return this.projectCategorys;
    }

    public void setProjectCategorys(List<ProjectCategoryCriteria> projectCategorys){
        this.projectCategorys = projectCategorys;
    }
}
