import {Component, OnInit} from '@angular/core';


import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {AbstractService} from 'src/app/zynerator/service/AbstractService';
import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';
import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';
import {ConfirmationService, MessageService,MenuItem} from 'primeng/api';
import {FileTempDto} from 'src/app/zynerator/dto/FileTempDto.model';


import {ProjectTeamAdminService} from 'src/app/shared/service/admin/project/ProjectTeamAdmin.service';
import {ProjectTeamDto} from 'src/app/shared/model/project/ProjectTeam.model';
import {ProjectTeamCriteria} from 'src/app/shared/criteria/project/ProjectTeamCriteria.model';

import {ProjectDto} from 'src/app/shared/model/project/Project.model';
import {ProjectAdminService} from 'src/app/shared/service/admin/project/ProjectAdmin.service';
import {RoleDto} from 'src/app/shared/model/role/Role.model';
import {RoleAdminService} from 'src/app/shared/service/admin/role/RoleAdmin.service';
import {MemberDto} from 'src/app/shared/model/member/Member.model';
import {MemberAdminService} from 'src/app/shared/service/admin/member/MemberAdmin.service';
@Component({
  selector: 'app-project-team-view-admin',
  templateUrl: './project-team-view-admin.component.html'
})
export class ProjectTeamViewAdminComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;


    members = new MemberDto();
    memberss: Array<MemberDto> = [];

    constructor(private service: ProjectTeamAdminService, private projectService: ProjectAdminService, private memberService: MemberAdminService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
    }


    get project(): ProjectDto {
        return this.projectService.item;
    }
    set project(value: ProjectDto) {
        this.projectService.item = value;
    }
    get projects(): Array<ProjectDto> {
        return this.projectService.items;
    }
    set projects(value: Array<ProjectDto>) {
        this.projectService.items = value;
    }

    public hideViewDialog() {
        this.viewDialog = false;
    }

    get items(): Array<ProjectTeamDto> {
        return this.service.items;
    }

    set items(value: Array<ProjectTeamDto>) {
        this.service.items = value;
    }

    get item(): ProjectTeamDto {
        return this.service.item;
    }

    set item(value: ProjectTeamDto) {
        this.service.item = value;
    }

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): ProjectTeamCriteria {
        return this.service.criteria;
    }

    set criteria(value: ProjectTeamCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
