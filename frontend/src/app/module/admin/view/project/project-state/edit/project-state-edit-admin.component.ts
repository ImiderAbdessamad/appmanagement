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




import {ProjectStateAdminService} from 'src/app/shared/service/admin/project/ProjectStateAdmin.service';
import {ProjectStateDto} from 'src/app/shared/model/project/ProjectState.model';
import {ProjectStateCriteria} from 'src/app/shared/criteria/project/ProjectStateCriteria.model';



@Component({
  selector: 'app-project-state-edit-admin',
  templateUrl: './project-state-edit-admin.component.html'
})
export class ProjectStateEditAdminComponent implements OnInit {

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



    private _validProjectStateCode = true;
    private _validProjectStateLibelle = true;
    private _validProjectStateStyle = true;




    constructor(private service: ProjectStateAdminService , @Inject(PLATFORM_ID) private platformId?) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
    }

    public prepareEdit() {
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
            this.item = new ProjectStateDto();
        } , error =>{
            console.log(error);
        });
    }

    public hideEditDialog() {
        this.editDialog = false;
        this.setValidation(true);
    }





    public setValidation(value: boolean){
        this.validProjectStateCode = value;
        this.validProjectStateLibelle = value;
        this.validProjectStateStyle = value;
    }

    public validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateProjectStateCode();
        this.validateProjectStateLibelle();
        this.validateProjectStateStyle();
    }

    public validateProjectStateCode(){
        if (this.stringUtilService.isEmpty(this.item.code)) {
            this.errorMessages.push('Code non valide');
            this.validProjectStateCode = false;
        } else {
            this.validProjectStateCode = true;
        }
    }

    public validateProjectStateLibelle(){
        if (this.stringUtilService.isEmpty(this.item.libelle)) {
            this.errorMessages.push('Libelle non valide');
            this.validProjectStateLibelle = false;
        } else {
            this.validProjectStateLibelle = true;
        }
    }

    public validateProjectStateStyle(){
        if (this.stringUtilService.isEmpty(this.item.style)) {
            this.errorMessages.push('Style non valide');
            this.validProjectStateStyle = false;
        } else {
            this.validProjectStateStyle = true;
        }
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


	get items(): Array<ProjectStateDto> {
        return this.service.items;
    }

    set items(value: Array<ProjectStateDto>) {
        this.service.items = value;
    }

    get item(): ProjectStateDto {
        return this.service.item;
    }

    set item(value: ProjectStateDto) {
        this.service.item = value;
    }

    get editDialog(): boolean {
        return this.service.editDialog;
    }

    set editDialog(value: boolean) {
        this.service.editDialog = value;
    }

    get criteria(): ProjectStateCriteria {
        return this.service.criteria;
    }

    set criteria(value: ProjectStateCriteria) {
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
