package  ma.zyn.app.dao.specification.core.member;

import ma.zyn.app.dao.criteria.core.member.MemberCriteria;
import ma.zyn.app.bean.core.member.Member;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class MemberSpecification extends  AbstractSpecification<MemberCriteria, Member>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("firstName", criteria.getFirstName(),criteria.getFirstNameLike());
        addPredicate("lastName", criteria.getLastName(),criteria.getLastNameLike());
        addPredicate("email", criteria.getEmail(),criteria.getEmailLike());
        addPredicate("phone", criteria.getPhone(),criteria.getPhoneLike());
    }

    public MemberSpecification(MemberCriteria criteria) {
        super(criteria);
    }

    public MemberSpecification(MemberCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
