package  ma.zyn.app.dao.criteria.core.project;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class ProjectTeamCriteria extends  BaseCriteria  {

    private String name;
    private String nameLike;
    private String description;
    private String descriptionLike;

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
