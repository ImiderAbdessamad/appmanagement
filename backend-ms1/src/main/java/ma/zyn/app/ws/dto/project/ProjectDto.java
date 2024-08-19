package  ma.zyn.app.ws.dto.project;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectDto  extends AuditBaseDto {

    private String code  ;
    private String name  ;
    private String description  ;
    private String startDate ;
    private String endDate ;

    private ProjectStateDto projectState ;
    private ProjectTeamDto projectTeam ;
    private ProjectTypeDto projectType ;
    private ProjectCategoryDto projectCategory ;



    public ProjectDto(){
        super();
    }




    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }


    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }


    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getStartDate(){
        return this.startDate;
    }
    public void setStartDate(String startDate){
        this.startDate = startDate;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getEndDate(){
        return this.endDate;
    }
    public void setEndDate(String endDate){
        this.endDate = endDate;
    }


    public ProjectStateDto getProjectState(){
        return this.projectState;
    }

    public void setProjectState(ProjectStateDto projectState){
        this.projectState = projectState;
    }
    public ProjectTeamDto getProjectTeam(){
        return this.projectTeam;
    }

    public void setProjectTeam(ProjectTeamDto projectTeam){
        this.projectTeam = projectTeam;
    }
    public ProjectTypeDto getProjectType(){
        return this.projectType;
    }

    public void setProjectType(ProjectTypeDto projectType){
        this.projectType = projectType;
    }
    public ProjectCategoryDto getProjectCategory(){
        return this.projectCategory;
    }

    public void setProjectCategory(ProjectCategoryDto projectCategory){
        this.projectCategory = projectCategory;
    }






}
