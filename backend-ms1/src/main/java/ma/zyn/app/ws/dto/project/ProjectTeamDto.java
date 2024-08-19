package  ma.zyn.app.ws.dto.project;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;


import ma.zyn.app.ws.dto.role.RoleDto;
import ma.zyn.app.ws.dto.member.MemberDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectTeamDto  extends AuditBaseDto {

    private String name  ;
    private String description  ;

    private ProjectDto project ;

    private List<MemberDto> members ;


    public ProjectTeamDto(){
        super();
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


    public ProjectDto getProject(){
        return this.project;
    }

    public void setProject(ProjectDto project){
        this.project = project;
    }



    public List<MemberDto> getMembers(){
        return this.members;
    }

    public void setMembers(List<MemberDto> members){
        this.members = members;
    }



}
