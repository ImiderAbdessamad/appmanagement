import {ProjectCriteria} from '../project/ProjectCriteria.model';
import {TaskStateCriteria} from './TaskStateCriteria.model';
import {MemberCriteria} from '../member/MemberCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class TaskCriteria extends BaseCriteria {

    public id: number;
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
  public taskState: TaskStateCriteria ;
  public taskStates: Array<TaskStateCriteria> ;
  public project: ProjectCriteria ;
  public projects: Array<ProjectCriteria> ;
      public subTasks: Array<TaskCriteria>;

}
