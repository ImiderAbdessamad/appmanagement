package ma.zyn.app.bean.core.project;


import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;




import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "project")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="project_seq",sequenceName="project_seq",allocationSize=1, initialValue = 1)
public class Project  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String name;

    @Column(length = 500)
    private String description;

    private LocalDateTime startDate ;

    private LocalDateTime endDate ;

    private ProjectState projectState ;
    private ProjectTeam projectTeam ;
    private ProjectType projectType ;
    private ProjectCategory projectCategory ;


    public Project(){
        super();
    }

    public Project(Long id){
        this.id = id;
    }

    public Project(Long id,String code){
        this.id = id;
        this.code = code ;
    }
    public Project(String code){
        this.code = code ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="project_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
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
    public LocalDateTime getStartDate(){
        return this.startDate;
    }
    public void setStartDate(LocalDateTime startDate){
        this.startDate = startDate;
    }
    public LocalDateTime getEndDate(){
        return this.endDate;
    }
    public void setEndDate(LocalDateTime endDate){
        this.endDate = endDate;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_state")
    public ProjectState getProjectState(){
        return this.projectState;
    }
    public void setProjectState(ProjectState projectState){
        this.projectState = projectState;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_team")
    public ProjectTeam getProjectTeam(){
        return this.projectTeam;
    }
    public void setProjectTeam(ProjectTeam projectTeam){
        this.projectTeam = projectTeam;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_type")
    public ProjectType getProjectType(){
        return this.projectType;
    }
    public void setProjectType(ProjectType projectType){
        this.projectType = projectType;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_category")
    public ProjectCategory getProjectCategory(){
        return this.projectCategory;
    }
    public void setProjectCategory(ProjectCategory projectCategory){
        this.projectCategory = projectCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id != null && id.equals(project.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

