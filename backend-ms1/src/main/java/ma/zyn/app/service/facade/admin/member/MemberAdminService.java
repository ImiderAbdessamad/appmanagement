package ma.zyn.app.service.facade.admin.member;

import java.util.List;
import ma.zyn.app.bean.core.member.Member;
import ma.zyn.app.dao.criteria.core.member.MemberCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface MemberAdminService {







	Member create(Member t);

    Member update(Member t);

    List<Member> update(List<Member> ts,boolean createIfNotExist);

    Member findById(Long id);

    Member findOrSave(Member t);

    Member findByReferenceEntity(Member t);

    Member findWithAssociatedLists(Long id);

    List<Member> findAllOptimized();

    List<Member> findAll();

    List<Member> findByCriteria(MemberCriteria criteria);

    List<Member> findPaginatedByCriteria(MemberCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(MemberCriteria criteria);

    List<Member> delete(List<Member> ts);

    boolean deleteById(Long id);

    List<List<Member>> getToBeSavedAndToBeDeleted(List<Member> oldList, List<Member> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
