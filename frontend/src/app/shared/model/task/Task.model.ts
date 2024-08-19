import {ProjectDto} from '../project/Project.model';
import {TaskStateDto} from './TaskState.model';
import {MemberDto} from '../member/Member.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class TaskDto extends BaseDto{

    public name: string;

    public description: string;

   public startDate: Date;

   public endDate: Date;

    public taskState: TaskStateDto ;
    public assignedTo: MemberDto ;
    public project: ProjectDto ;
     public subTasks: Array<TaskDto>;


    constructor() {
        super();

        this.name = '';
        this.description = '';
        this.startDate = null;
        this.endDate = null;
        this.taskState = new TaskStateDto() ;
        this.project = new ProjectDto() ;
        this.subTasks = new Array<TaskDto>();

        }

}
