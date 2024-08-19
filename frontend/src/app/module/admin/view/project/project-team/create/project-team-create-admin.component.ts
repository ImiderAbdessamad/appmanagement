import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';

import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




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
  selector: 'app-project-team-create-admin',
  templateUrl: './project-team-create-admin.component.html'
})
export class ProjectTeamCreateAdminComponent  implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;

    private _membersElement = new MemberDto();


    private _validProjectCode = true;

	constructor(private service: ProjectTeamAdminService , private projectService: ProjectAdminService, private memberService: MemberAdminService, @Inject(PLATFORM_ID) private platformId? ) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
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
                this.item = new ProjectTeamDto();
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



    validateMembers(){
        this.errorMessages = new Array();
    }


    public  setValidation(value: boolean){
    }

    public addMembers() {
        if( this.item.members == null )
            this.item.members = new Array<MemberDto>();
       this.validateMembers();
       if (this.errorMessages.length === 0) {
              this.item.members.push({... this.membersElement});
              this.membersElement = new MemberDto();
       }else{
            this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs suivant : ' + this.errorMessages});
       }
    }


    public deletemembers(p: MemberDto) {
        this.item.members.forEach((element, index) => {
            if (element === p) { this.item.members.splice(index, 1); }
        });
    }

    public editmembers(p: MemberDto) {
        this.membersElement = {... p};
        this.activeTab = 0;
    }


    public  validateForm(): void{
        this.errorMessages = new Array<string>();
    }



    public async openCreateProject(project: string) {
    const isPermistted = await this.roleService.isPermitted('Project', 'add');
    if(isPermistted) {
         this.project = new ProjectDto();
         this.createProjectDialog = true;
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




    get validProjectCode(): boolean {
        return this._validProjectCode;
    }
    set validProjectCode(value: boolean) {
        this._validProjectCode = value;
    }

    get membersElement(): MemberDto {
        if( this._membersElement == null )
            this._membersElement = new MemberDto();
        return this._membersElement;
    }

    set membersElement(value: MemberDto) {
        this._membersElement = value;
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

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): ProjectTeamCriteria {
        return this.service.criteria;
    }

    set criteria(value: ProjectTeamCriteria) {
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
