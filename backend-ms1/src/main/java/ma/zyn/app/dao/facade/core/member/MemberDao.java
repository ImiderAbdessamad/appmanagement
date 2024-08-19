package ma.zyn.app.dao.facade.core.member;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.member.Member;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface MemberDao extends AbstractRepository<Member,Long>  {



}
