package ma.zyn.app.bean.core.member;

import java.util.List;





import ma.zyn.app.bean.core.project.Project;
import ma.zyn.app.bean.core.project.ProjectTeam;
import ma.zyn.app.bean.core.role.Role;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "member")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="member_seq",sequenceName="member_seq",allocationSize=1, initialValue = 1)
public class Member  extends BaseEntity     {




    @Column(length = 500)
    private String firstName;

    @Column(length = 500)
    private String lastName;

    @Column(length = 500)
    private String email;

    @Column(length = 500)
    private String phone;


    private List<Role> roles ;
    private List<ProjectTeam> projectTeams ;

    public Member(){
        super();
    }

    public Member(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="member_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
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
    @OneToMany(mappedBy = "member")
    public List<Role> getRoles(){
        return this.roles;
    }

    public void setRoles(List<Role> roles){
        this.roles = roles;
    }
    @OneToMany(mappedBy = "member")
    public List<ProjectTeam> getProjectTeams(){
        return this.projectTeams;
    }

    public void setProjectTeams(List<ProjectTeam> projectTeams){
        this.projectTeams = projectTeams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return id != null && id.equals(member.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

