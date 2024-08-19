import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';

import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




import {MemberAdminService} from 'src/app/shared/service/admin/member/MemberAdmin.service';
import {MemberDto} from 'src/app/shared/model/member/Member.model';
import {MemberCriteria} from 'src/app/shared/criteria/member/MemberCriteria.model';
import {ProjectDto} from 'src/app/shared/model/project/Project.model';
import {ProjectAdminService} from 'src/app/shared/service/admin/project/ProjectAdmin.service';
import {ProjectTeamDto} from 'src/app/shared/model/project/ProjectTeam.model';
import {ProjectTeamAdminService} from 'src/app/shared/service/admin/project/ProjectTeamAdmin.service';
import {RoleDto} from 'src/app/shared/model/role/Role.model';
import {RoleAdminService} from 'src/app/shared/service/admin/role/RoleAdmin.service';
@Component({
  selector: 'app-member-create-admin',
  templateUrl: './member-create-admin.component.html'
})
export class MemberCreateAdminComponent  implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;

    private _rolesElement = new RoleDto();
    private _projectTeamsElement = new ProjectTeamDto();


    private _validRolesCode = true;
    private _validRolesLibelle = true;

	constructor(private service: MemberAdminService , private projectService: ProjectAdminService, private projectTeamService: ProjectTeamAdminService, private roleService: RoleAdminService, @Inject(PLATFORM_ID) private platformId? ) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.projectTeamsElement.project = new ProjectDto();
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
                this.item = new MemberDto();
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



    validateRoles(){
        this.errorMessages = new Array();
        this.validateRolesCode();
        this.validateRolesLibelle();
    }
    validateProjectTeams(){
        this.errorMessages = new Array();
    }


    public  setValidation(value: boolean){
        this.validRolesCode = value;
        this.validRolesLibelle = value;
    }

    public addRoles() {
        if( this.item.roles == null )
            this.item.roles = new Array<RoleDto>();
       this.validateRoles();
       if (this.errorMessages.length === 0) {
              this.item.roles.push({... this.rolesElement});
              this.rolesElement = new RoleDto();
       }else{
            this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs suivant : ' + this.errorMessages});
       }
    }


    public deleteroles(p: RoleDto) {
        this.item.roles.forEach((element, index) => {
            if (element === p) { this.item.roles.splice(index, 1); }
        });
    }

    public editroles(p: RoleDto) {
        this.rolesElement = {... p};
        this.activeTab = 0;
    }
    public addProjectTeams() {
        if( this.item.projectTeams == null )
            this.item.projectTeams = new Array<ProjectTeamDto>();
       this.validateProjectTeams();
       if (this.errorMessages.length === 0) {
              this.item.projectTeams.push({... this.projectTeamsElement});
              this.projectTeamsElement = new ProjectTeamDto();
       }else{
            this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs suivant : ' + this.errorMessages});
       }
    }


    public deleteprojectTeams(p: ProjectTeamDto) {
        this.item.projectTeams.forEach((element, index) => {
            if (element === p) { this.item.projectTeams.splice(index, 1); }
        });
    }

    public editprojectTeams(p: ProjectTeamDto) {
        this.projectTeamsElement = {... p};
        this.activeTab = 0;
    }


    public  validateForm(): void{
        this.errorMessages = new Array<string>();
    }


    public validateRolesCode(){
        if (this.rolesElement.code == null) {
            this.errorMessages.push('Code de la role est  invalide');
            this.validRolesCode = false;
        } else {
            this.validRolesCode = true;
        }
    }
    public validateRolesLibelle(){
        if (this.rolesElement.libelle == null) {
            this.errorMessages.push('Libelle de la role est  invalide');
            this.validRolesLibelle = false;
        } else {
            this.validRolesLibelle = true;
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




    get validRolesCode(): boolean {
        return this._validRolesCode;
    }
    set validRolesCode(value: boolean) {
        this._validRolesCode = value;
    }
    get validRolesLibelle(): boolean {
        return this._validRolesLibelle;
    }
    set validRolesLibelle(value: boolean) {
        this._validRolesLibelle = value;
    }

    get rolesElement(): RoleDto {
        if( this._rolesElement == null )
            this._rolesElement = new RoleDto();
        return this._rolesElement;
    }

    set rolesElement(value: RoleDto) {
        this._rolesElement = value;
    }
    get projectTeamsElement(): ProjectTeamDto {
        if( this._projectTeamsElement == null )
            this._projectTeamsElement = new ProjectTeamDto();
        return this._projectTeamsElement;
    }

    set projectTeamsElement(value: ProjectTeamDto) {
        this._projectTeamsElement = value;
    }

    get items(): Array<MemberDto> {
        return this.service.items;
    }

    set items(value: Array<MemberDto>) {
        this.service.items = value;
    }

    get item(): MemberDto {
        return this.service.item;
    }

    set item(value: MemberDto) {
        this.service.item = value;
    }

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): MemberCriteria {
        return this.service.criteria;
    }

    set criteria(value: MemberCriteria) {
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
