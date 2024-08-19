import {ProjectTeamCriteria} from '../project/ProjectTeamCriteria.model';
import {RoleCriteria} from '../role/RoleCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class MemberCriteria extends BaseCriteria {

    public id: number;
    public firstName: string;
    public firstNameLike: string;
    public lastName: string;
    public lastNameLike: string;
    public email: string;
    public emailLike: string;
    public phone: string;
    public phoneLike: string;
      public roles: Array<RoleCriteria>;
      public projectTeams: Array<ProjectTeamCriteria>;

}
