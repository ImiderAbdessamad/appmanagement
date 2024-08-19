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


import {TaskAdminService} from 'src/app/shared/service/admin/task/TaskAdmin.service';
import {TaskDto} from 'src/app/shared/model/task/Task.model';
import {TaskCriteria} from 'src/app/shared/criteria/task/TaskCriteria.model';

import {ProjectDto} from 'src/app/shared/model/project/Project.model';
import {ProjectAdminService} from 'src/app/shared/service/admin/project/ProjectAdmin.service';
import {TaskStateDto} from 'src/app/shared/model/task/TaskState.model';
import {TaskStateAdminService} from 'src/app/shared/service/admin/task/TaskStateAdmin.service';
import {MemberDto} from 'src/app/shared/model/member/Member.model';
import {MemberAdminService} from 'src/app/shared/service/admin/member/MemberAdmin.service';
@Component({
  selector: 'app-task-view-admin',
  templateUrl: './task-view-admin.component.html'
})
export class TaskViewAdminComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;


    subTasks = new TaskDto();
    subTaskss: Array<TaskDto> = [];

    constructor(private service: TaskAdminService, private projectService: ProjectAdminService, private taskStateService: TaskStateAdminService, private memberService: MemberAdminService){
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
    get assignedTo(): MemberDto {
        return this.memberService.item;
    }
    set assignedTo(value: MemberDto) {
        this.memberService.item = value;
    }
    get assignedTos(): Array<MemberDto> {
        return this.memberService.items;
    }
    set assignedTos(value: Array<MemberDto>) {
        this.memberService.items = value;
    }
    get taskState(): TaskStateDto {
        return this.taskStateService.item;
    }
    set taskState(value: TaskStateDto) {
        this.taskStateService.item = value;
    }
    get taskStates(): Array<TaskStateDto> {
        return this.taskStateService.items;
    }
    set taskStates(value: Array<TaskStateDto>) {
        this.taskStateService.items = value;
    }

    public hideViewDialog() {
        this.viewDialog = false;
    }

    get items(): Array<TaskDto> {
        return this.service.items;
    }

    set items(value: Array<TaskDto>) {
        this.service.items = value;
    }

    get item(): TaskDto {
        return this.service.item;
    }

    set item(value: TaskDto) {
        this.service.item = value;
    }

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): TaskCriteria {
        return this.service.criteria;
    }

    set criteria(value: TaskCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
