import {ProjectCriteria} from './ProjectCriteria.model';
import {MemberCriteria} from '../member/MemberCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class ProjectTeamCriteria extends BaseCriteria {

    public id: number;
    public name: string;
    public nameLike: string;
    public description: string;
    public descriptionLike: string;
      public members: Array<MemberCriteria>;

}
