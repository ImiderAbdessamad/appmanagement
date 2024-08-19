package ma.zyn.app.bean.core.project;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "project_type")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="project_type_seq",sequenceName="project_type_seq",allocationSize=1, initialValue = 1)
public class ProjectType  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;



    public ProjectType(){
        super();
    }

    public ProjectType(Long id){
        this.id = id;
    }

    public ProjectType(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public ProjectType(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="project_type_seq")
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
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectType projectType = (ProjectType) o;
        return id != null && id.equals(projectType.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

