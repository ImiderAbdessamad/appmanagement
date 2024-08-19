import {ProjectDto} from './Project.model';
import {MemberDto} from '../member/Member.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class ProjectTeamDto extends BaseDto{

    public name: string;

    public description: string;

    public project: ProjectDto ;
     public members: Array<MemberDto>;


    constructor() {
        super();

        this.name = '';
        this.description = '';
        this.members = new Array<MemberDto>();

        }

}
