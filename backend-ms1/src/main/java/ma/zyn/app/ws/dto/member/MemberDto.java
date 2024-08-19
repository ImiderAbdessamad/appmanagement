package  ma.zyn.app.ws.dto.member;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;


import ma.zyn.app.ws.dto.project.ProjectDto;
import ma.zyn.app.ws.dto.project.ProjectTeamDto;
import ma.zyn.app.ws.dto.role.RoleDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberDto  extends AuditBaseDto {

    private String firstName  ;
    private String lastName  ;
    private String email  ;
    private String phone  ;


    private List<RoleDto> roles ;
    private List<ProjectTeamDto> projectTeams ;


    public MemberDto(){
        super();
    }




    public String getFirstName(){
        return this.firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }


    public String getLastName(){
        return this.lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }


    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }


    public String getPhone(){
        return this.phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }





    public List<RoleDto> getRoles(){
        return this.roles;
    }

    public void setRoles(List<RoleDto> roles){
        this.roles = roles;
    }
    public List<ProjectTeamDto> getProjectTeams(){
        return this.projectTeams;
    }

    public void setProjectTeams(List<ProjectTeamDto> projectTeams){
        this.projectTeams = projectTeams;
    }



}
