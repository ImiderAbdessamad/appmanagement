import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';
import {FileTempDto} from 'src/app/zynerator/dto/FileTempDto.model';
import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';

import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




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
  selector: 'app-project-edit-admin',
  templateUrl: './project-edit-admin.component.html'
})
export class ProjectEditAdminComponent implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();


    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;
    private _file: any;
    private _files: any;



    private _validProjectCode = true;

    private _validProjectStateCode = true;
    private _validProjectStateLibelle = true;
    private _validProjectStateStyle = true;
    private _validProjectTypeCode = true;
    private _validProjectTypeLibelle = true;
    private _validProjectCategoryCode = true;
    private _validProjectCategoryLibelle = true;



    constructor(private service: ProjectAdminService , private projectTeamService: ProjectTeamAdminService, private projectStateService: ProjectStateAdminService, private projectCategoryService: ProjectCategoryAdminService, private projectTypeService: ProjectTypeAdminService, @Inject(PLATFORM_ID) private platformId?) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.projectStateService.findAll().subscribe((data) => this.projectStates = data);
        this.projectTeamService.findAll().subscribe((data) => this.projectTeams = data);
        this.projectTypeService.findAll().subscribe((data) => this.projectTypes = data);
        this.projectCategoryService.findAll().subscribe((data) => this.projectCategorys = data);
    }

    public prepareEdit() {
        this.item.startDate = this.service.format(this.item.startDate);
        this.item.endDate = this.service.format(this.item.endDate);
    }



 public edit(): void {
        this.submitted = true;
        this.prepareEdit();
        this.validateForm();
        if (this.errorMessages.length === 0) {
            this.editWithShowOption(false);
        } else {
            this.messageService.add({
                severity: 'error',
                summary: 'Erreurs',
                detail: 'Merci de corrigé les erreurs sur le formulaire'
            });
        }
    }

    public editWithShowOption(showList: boolean) {
        this.service.edit().subscribe(religion=>{
            const myIndex = this.items.findIndex(e => e.id === this.item.id);
            this.items[myIndex] = religion;
            this.editDialog = false;
            this.submitted = false;
            this.item = new ProjectDto();
        } , error =>{
            console.log(error);
        });
    }

    public hideEditDialog() {
        this.editDialog = false;
        this.setValidation(true);
    }





    public setValidation(value: boolean){
        this.validProjectCode = value;
    }

    public validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateProjectCode();
    }

    public validateProjectCode(){
        if (this.stringUtilService.isEmpty(this.item.code)) {
            this.errorMessages.push('Code non valide');
            this.validProjectCode = false;
        } else {
            this.validProjectCode = true;
        }
    }




   public async openCreateProjectTeam(projectTeam: string) {
        const isPermistted = await this.roleService.isPermitted('ProjectTeam', 'edit');
        if (isPermistted) {
             this.projectTeam = new ProjectTeamDto();
             this.createProjectTeamDialog = true;
        }else {
             this.messageService.add({
                severity: 'error', summary: 'erreur', detail: 'problème de permission'
            });
        }
    }
   public async openCreateProjectCategory(projectCategory: string) {
        const isPermistted = await this.roleService.isPermitted('ProjectCategory', 'edit');
        if (isPermistted) {
             this.projectCategory = new ProjectCategoryDto();
             this.createProjectCategoryDialog = true;
        }else {
             this.messageService.add({
                severity: 'error', summary: 'erreur', detail: 'problème de permission'
            });
        }
    }
   public async openCreateProjectType(projectType: string) {
        const isPermistted = await this.roleService.isPermitted('ProjectType', 'edit');
        if (isPermistted) {
             this.projectType = new ProjectTypeDto();
             this.createProjectTypeDialog = true;
        }else {
             this.messageService.add({
                severity: 'error', summary: 'erreur', detail: 'problème de permission'
            });
        }
    }
   public async openCreateProjectState(projectState: string) {
        const isPermistted = await this.roleService.isPermitted('ProjectState', 'edit');
        if (isPermistted) {
             this.projectState = new ProjectStateDto();
             this.createProjectStateDialog = true;
        }else {
             this.messageService.add({
                severity: 'error', summary: 'erreur', detail: 'problème de permission'
            });
        }
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
    get createProjectTeamDialog(): boolean {
        return this.projectTeamService.createDialog;
    }
    set createProjectTeamDialog(value: boolean) {
        this.projectTeamService.createDialog= value;
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
    get createProjectCategoryDialog(): boolean {
        return this.projectCategoryService.createDialog;
    }
    set createProjectCategoryDialog(value: boolean) {
        this.projectCategoryService.createDialog= value;
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
    get createProjectTypeDialog(): boolean {
        return this.projectTypeService.createDialog;
    }
    set createProjectTypeDialog(value: boolean) {
        this.projectTypeService.createDialog= value;
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
    get createProjectStateDialog(): boolean {
        return this.projectStateService.createDialog;
    }
    set createProjectStateDialog(value: boolean) {
        this.projectStateService.createDialog= value;
    }


    get validProjectCode(): boolean {
        return this._validProjectCode;
    }
    set validProjectCode(value: boolean) {
        this._validProjectCode = value;
    }

    get validProjectStateCode(): boolean {
        return this._validProjectStateCode;
    }
    set validProjectStateCode(value: boolean) {
        this._validProjectStateCode = value;
    }
    get validProjectStateLibelle(): boolean {
        return this._validProjectStateLibelle;
    }
    set validProjectStateLibelle(value: boolean) {
        this._validProjectStateLibelle = value;
    }
    get validProjectStateStyle(): boolean {
        return this._validProjectStateStyle;
    }
    set validProjectStateStyle(value: boolean) {
        this._validProjectStateStyle = value;
    }
    get validProjectTypeCode(): boolean {
        return this._validProjectTypeCode;
    }
    set validProjectTypeCode(value: boolean) {
        this._validProjectTypeCode = value;
    }
    get validProjectTypeLibelle(): boolean {
        return this._validProjectTypeLibelle;
    }
    set validProjectTypeLibelle(value: boolean) {
        this._validProjectTypeLibelle = value;
    }
    get validProjectCategoryCode(): boolean {
        return this._validProjectCategoryCode;
    }
    set validProjectCategoryCode(value: boolean) {
        this._validProjectCategoryCode = value;
    }
    get validProjectCategoryLibelle(): boolean {
        return this._validProjectCategoryLibelle;
    }
    set validProjectCategoryLibelle(value: boolean) {
        this._validProjectCategoryLibelle = value;
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

    get editDialog(): boolean {
        return this.service.editDialog;
    }

    set editDialog(value: boolean) {
        this.service.editDialog = value;
    }

    get criteria(): ProjectCriteria {
        return this.service.criteria;
    }

    set criteria(value: ProjectCriteria) {
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
