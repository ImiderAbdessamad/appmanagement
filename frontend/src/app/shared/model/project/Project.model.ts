import {ProjectTeamDto} from './ProjectTeam.model';
import {ProjectStateDto} from './ProjectState.model';
import {ProjectCategoryDto} from './ProjectCategory.model';
import {ProjectTypeDto} from './ProjectType.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class ProjectDto extends BaseDto{

    public code: string;

    public name: string;

    public description: string;

   public startDate: Date;

   public endDate: Date;

    public projectState: ProjectStateDto ;
    public projectTeam: ProjectTeamDto ;
    public projectType: ProjectTypeDto ;
    public projectCategory: ProjectCategoryDto ;


    constructor() {
        super();

        this.code = '';
        this.name = '';
        this.description = '';
        this.startDate = null;
        this.endDate = null;
        this.projectState = new ProjectStateDto() ;
        this.projectTeam = new ProjectTeamDto() ;
        this.projectType = new ProjectTypeDto() ;

        }

}
