package ma.zyn.app.dao.facade.core.role;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.role.Role;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.role.Role;
import java.util.List;


@Repository
public interface RoleDao extends AbstractRepository<Role,Long>  {
    Role findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Role(item.id,item.libelle) FROM Role item")
    List<Role> findAllOptimized();

}
