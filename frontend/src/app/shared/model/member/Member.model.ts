import {ProjectTeamDto} from '../project/ProjectTeam.model';
import {RoleDto} from '../role/Role.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class MemberDto extends BaseDto{

    public firstName: string;

    public lastName: string;

    public email: string;

    public phone: string;

     public roles: Array<RoleDto>;
     public projectTeams: Array<ProjectTeamDto>;


    constructor() {
        super();

        this.firstName = '';
        this.lastName = '';
        this.email = '';
        this.phone = '';
        this.roles = new Array<RoleDto>();
        this.projectTeams = new Array<ProjectTeamDto>();

        }

}
