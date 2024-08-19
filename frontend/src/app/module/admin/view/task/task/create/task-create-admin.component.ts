import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';

import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




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
  selector: 'app-task-create-admin',
  templateUrl: './task-create-admin.component.html'
})
export class TaskCreateAdminComponent  implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;

    private _subTasksElement = new TaskDto();


    private _validTaskStateCode = true;
    private _validTaskStateLibelle = true;
    private _validTaskStateStyle = true;
    private _validProjectCode = true;

	constructor(private service: TaskAdminService , private projectService: ProjectAdminService, private taskStateService: TaskStateAdminService, private memberService: MemberAdminService, @Inject(PLATFORM_ID) private platformId? ) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.subTasksElement.taskState = new TaskStateDto();
        this.taskStateService.findAll().subscribe((data) => this.taskStates = data);
        this.subTasksElement.assignedTo = new MemberDto();
        this.memberService.findAll().subscribe((data) => this.assignedTos = data);
        this.subTasksElement.project = new ProjectDto();
        this.projectService.findAll().subscribe((data) => this.projects = data);
        this.taskStateService.findAll().subscribe((data) => this.taskStates = data);
        this.memberService.findAll().subscribe((data) => this.assignedTos = data);
        this.projectService.findAll().subscribe((data) => this.projects = data);
    }



    public save(): void {
        this.submitted = true;
        this.validateForm();
        if (this.errorMessages.length === 0) {
            this.saveWithShowOption(false);
        } else {
            this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs sur le formulaire'});
        }
    }

    public saveWithShowOption(showList: boolean) {
        this.service.save().subscribe(item => {
            if (item != null) {
                this.items.push({...item});
                this.createDialog = false;
                this.submitted = false;
                this.item = new TaskDto();
            } else {
                this.messageService.add({severity: 'error', summary: 'Erreurs', detail: 'Element existant'});
            }

        }, error => {
            console.log(error);
        });
    }


    public hideCreateDialog() {
        this.createDialog = false;
        this.setValidation(true);
    }



    validateSubTasks(){
        this.errorMessages = new Array();
    }


    public  setValidation(value: boolean){
    }

    public addSubTasks() {
        if( this.item.subTasks == null )
            this.item.subTasks = new Array<TaskDto>();
       this.validateSubTasks();
       if (this.errorMessages.length === 0) {
              this.item.subTasks.push({... this.subTasksElement});
              this.subTasksElement = new TaskDto();
       }else{
            this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs suivant : ' + this.errorMessages});
       }
    }


    public deletesubTasks(p: TaskDto) {
        this.item.subTasks.forEach((element, index) => {
            if (element === p) { this.item.subTasks.splice(index, 1); }
        });
    }

    public editsubTasks(p: TaskDto) {
        this.subTasksElement = {... p};
        this.activeTab = 0;
    }


    public  validateForm(): void{
        this.errorMessages = new Array<string>();
    }



    public async openCreateTaskState(taskState: string) {
    const isPermistted = await this.roleService.isPermitted('TaskState', 'add');
    if(isPermistted) {
         this.taskState = new TaskStateDto();
         this.createTaskStateDialog = true;
    }else{
        this.messageService.add({
        severity: 'error', summary: 'erreur', detail: 'problème de permission'
        });
     }
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
    get createProjectDialog(): boolean {
        return this.projectService.createDialog;
    }
    set createProjectDialog(value: boolean) {
        this.projectService.createDialog= value;
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
    get createAssignedToDialog(): boolean {
        return this.memberService.createDialog;
    }
    set createAssignedToDialog(value: boolean) {
        this.memberService.createDialog= value;
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
    get createTaskStateDialog(): boolean {
        return this.taskStateService.createDialog;
    }
    set createTaskStateDialog(value: boolean) {
        this.taskStateService.createDialog= value;
    }




    get validTaskStateCode(): boolean {
        return this._validTaskStateCode;
    }
    set validTaskStateCode(value: boolean) {
        this._validTaskStateCode = value;
    }
    get validTaskStateLibelle(): boolean {
        return this._validTaskStateLibelle;
    }
    set validTaskStateLibelle(value: boolean) {
        this._validTaskStateLibelle = value;
    }
    get validTaskStateStyle(): boolean {
        return this._validTaskStateStyle;
    }
    set validTaskStateStyle(value: boolean) {
        this._validTaskStateStyle = value;
    }
    get validProjectCode(): boolean {
        return this._validProjectCode;
    }
    set validProjectCode(value: boolean) {
        this._validProjectCode = value;
    }

    get subTasksElement(): TaskDto {
        if( this._subTasksElement == null )
            this._subTasksElement = new TaskDto();
        return this._subTasksElement;
    }

    set subTasksElement(value: TaskDto) {
        this._subTasksElement = value;
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

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): TaskCriteria {
        return this.service.criteria;
    }

    set criteria(value: TaskCriteria) {
        this.service.criteria = value;
    }

    get dateFormat() {
        return environment.dateFormatCreate;
    }

    get dateFormatColumn() {
        return environment.dateFormatCreate;
    }

    get submitted(): boolean {
        return this._submitted;
    }

    set submitted(value: boolean) {
        this._submitted = value;
    }

    get errorMessages(): string[] {
        if (this._errorMessages == null) {
            this._errorMessages = new Array<string>();
        }
        return this._errorMessages;
    }

    set errorMessages(value: string[]) {
        this._errorMessages = value;
    }

    get validate(): boolean {
        return this.service.validate;
    }

    set validate(value: boolean) {
        this.service.validate = value;
    }


    get activeTab(): number {
        return this._activeTab;
    }

    set activeTab(value: number) {
        this._activeTab = value;
    }

}
