import {ProjectTeamCriteria} from './ProjectTeamCriteria.model';
import {ProjectStateCriteria} from './ProjectStateCriteria.model';
import {ProjectCategoryCriteria} from './ProjectCategoryCriteria.model';
import {ProjectTypeCriteria} from './ProjectTypeCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class ProjectCriteria extends BaseCriteria {

    public id: number;
    public code: string;
    public codeLike: string;
    public name: string;
    public nameLike: string;
    public description: string;
    public descriptionLike: string;
    public startDate: Date;
    public startDateFrom: Date;
    public startDateTo: Date;
    public endDate: Date;
    public endDateFrom: Date;
    public endDateTo: Date;
  public projectState: ProjectStateCriteria ;
  public projectStates: Array<ProjectStateCriteria> ;
  public projectTeam: ProjectTeamCriteria ;
  public projectTeams: Array<ProjectTeamCriteria> ;
  public projectType: ProjectTypeCriteria ;
  public projectTypes: Array<ProjectTypeCriteria> ;

}
