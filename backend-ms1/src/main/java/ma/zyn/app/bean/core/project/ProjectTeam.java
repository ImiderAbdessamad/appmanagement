package ma.zyn.app.bean.core.project;

import java.util.List;





import ma.zyn.app.bean.core.role.Role;
import ma.zyn.app.bean.core.member.Member;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "project_team")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="project_team_seq",sequenceName="project_team_seq",allocationSize=1, initialValue = 1)
public class ProjectTeam  extends BaseEntity     {




    @Column(length = 500)
    private String name;

    @Column(length = 500)
    private String description;

    private Project project ;

    private List<Member> members ;

    public ProjectTeam(){
        super();
    }

    public ProjectTeam(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="project_team_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project")
    public Project getProject(){
        return this.project;
    }
    public void setProject(Project project){
        this.project = project;
    }
    @OneToMany(mappedBy = "projectTeam")
    public List<Member> getMembers(){
        return this.members;
    }

    public void setMembers(List<Member> members){
        this.members = members;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectTeam projectTeam = (ProjectTeam) o;
        return id != null && id.equals(projectTeam.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

