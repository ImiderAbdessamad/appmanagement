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


import {ProjectAdminService} from 'src/app/shared/service/admin/project/ProjectAdmin.service';
import {ProjectDto} from 'src/app/shared/model/project/Project.model';
import {ProjectCriteria} from 'src/app/shared/criteria/project/ProjectCriteria.model';

import {ProjectTeamDto} from 'src/app/shared/model/project/ProjectTeam.model';
import {ProjectTeamAdminService} from 'src/app/shared/service/admin/project/ProjectTeamAdmin.service';
import {ProjectStateDto} from 'src/app/shared/model/project/ProjectState.model';
import {ProjectStateAdminService} from 'src/app/shared/service/admin/project/ProjectStateAdmin.service';
import {ProjectCategoryDto} from 'src/app/shared/model/project/ProjectCategory.model';
import {ProjectCategoryAdminService} from 'src/app/shared/service/admin/project/ProjectCategoryAdmin.service';
import {ProjectTypeDto} from 'src/app/shared/model/project/ProjectType.model';
import {ProjectTypeAdminService} from 'src/app/shared/service/admin/project/ProjectTypeAdmin.service';
@Component({
  selector: 'app-project-view-admin',
  templateUrl: './project-view-admin.component.html'
})
export class ProjectViewAdminComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;



    constructor(private service: ProjectAdminService, private projectTeamService: ProjectTeamAdminService, private projectStateService: ProjectStateAdminService, private projectCategoryService: ProjectCategoryAdminService, private projectTypeService: ProjectTypeAdminService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
    }


    get projectTeam(): ProjectTeamDto {
        return this.projectTeamService.item;
    }
    set projectTeam(value: ProjectTeamDto) {
        this.projectTeamService.item = value;
    }
    get projectTeams(): Array<ProjectTeamDto> {
        return this.projectTeamService.items;
    }
    set projectTeams(value: Array<ProjectTeamDto>) {
        this.projectTeamService.items = value;
    }
    get projectCategory(): ProjectCategoryDto {
        return this.projectCategoryService.item;
    }
    set projectCategory(value: ProjectCategoryDto) {
        this.projectCategoryService.item = value;
    }
    get projectCategorys(): Array<ProjectCategoryDto> {
        return this.projectCategoryService.items;
    }
    set projectCategorys(value: Array<ProjectCategoryDto>) {
        this.projectCategoryService.items = value;
    }
    get projectType(): ProjectTypeDto {
        return this.projectTypeService.item;
    }
    set projectType(value: ProjectTypeDto) {
        this.projectTypeService.item = value;
    }
    get projectTypes(): Array<ProjectTypeDto> {
        return this.projectTypeService.items;
    }
    set projectTypes(value: Array<ProjectTypeDto>) {
        this.projectTypeService.items = value;
    }
    get projectState(): ProjectStateDto {
        return this.projectStateService.item;
    }
    set projectState(value: ProjectStateDto) {
        this.projectStateService.item = value;
    }
    get projectStates(): Array<ProjectStateDto> {
        return this.projectStateService.items;
    }
    set projectStates(value: Array<ProjectStateDto>) {
        this.projectStateService.items = value;
    }

    public hideViewDialog() {
        this.viewDialog = false;
    }

    get items(): Array<ProjectDto> {
        return this.service.items;
    }

    set items(value: Array<ProjectDto>) {
        this.service.items = value;
    }

    get item(): ProjectDto {
        return this.service.item;
    }

    set item(value: ProjectDto) {
        this.service.item = value;
    }

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): ProjectCriteria {
        return this.service.criteria;
    }

    set criteria(value: ProjectCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
