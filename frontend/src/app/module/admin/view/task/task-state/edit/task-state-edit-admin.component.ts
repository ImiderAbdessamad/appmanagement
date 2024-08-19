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




import {TaskStateAdminService} from 'src/app/shared/service/admin/task/TaskStateAdmin.service';
import {TaskStateDto} from 'src/app/shared/model/task/TaskState.model';
import {TaskStateCriteria} from 'src/app/shared/criteria/task/TaskStateCriteria.model';



@Component({
  selector: 'app-task-state-edit-admin',
  templateUrl: './task-state-edit-admin.component.html'
})
export class TaskStateEditAdminComponent implements OnInit {

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



    private _validTaskStateCode = true;
    private _validTaskStateLibelle = true;
    private _validTaskStateStyle = true;




    constructor(private service: TaskStateAdminService , @Inject(PLATFORM_ID) private platformId?) {
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
            this.item = new TaskStateDto();
        } , error =>{
            console.log(error);
        });
    }

    public hideEditDialog() {
        this.editDialog = false;
        this.setValidation(true);
    }





    public setValidation(value: boolean){
        this.validTaskStateCode = value;
        this.validTaskStateLibelle = value;
        this.validTaskStateStyle = value;
    }

    public validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateTaskStateCode();
        this.validateTaskStateLibelle();
        this.validateTaskStateStyle();
    }

    public validateTaskStateCode(){
        if (this.stringUtilService.isEmpty(this.item.code)) {
            this.errorMessages.push('Code non valide');
            this.validTaskStateCode = false;
        } else {
            this.validTaskStateCode = true;
        }
    }

    public validateTaskStateLibelle(){
        if (this.stringUtilService.isEmpty(this.item.libelle)) {
            this.errorMessages.push('Libelle non valide');
            this.validTaskStateLibelle = false;
        } else {
            this.validTaskStateLibelle = true;
        }
    }

    public validateTaskStateStyle(){
        if (this.stringUtilService.isEmpty(this.item.style)) {
            this.errorMessages.push('Style non valide');
            this.validTaskStateStyle = false;
        } else {
            this.validTaskStateStyle = true;
        }
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


	get items(): Array<TaskStateDto> {
        return this.service.items;
    }

    set items(value: Array<TaskStateDto>) {
        this.service.items = value;
    }

    get item(): TaskStateDto {
        return this.service.item;
    }

    set item(value: TaskStateDto) {
        this.service.item = value;
    }

    get editDialog(): boolean {
        return this.service.editDialog;
    }

    set editDialog(value: boolean) {
        this.service.editDialog = value;
    }

    get criteria(): TaskStateCriteria {
        return this.service.criteria;
    }

    set criteria(value: TaskStateCriteria) {
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
