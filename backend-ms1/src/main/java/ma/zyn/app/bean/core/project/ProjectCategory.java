package ma.zyn.app.bean.core.project;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "project_category")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="project_category_seq",sequenceName="project_category_seq",allocationSize=1, initialValue = 1)
public class ProjectCategory  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;



    public ProjectCategory(){
        super();
    }

    public ProjectCategory(Long id){
        this.id = id;
    }

    public ProjectCategory(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public ProjectCategory(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="project_category_seq")
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
        ProjectCategory projectCategory = (ProjectCategory) o;
        return id != null && id.equals(projectCategory.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

