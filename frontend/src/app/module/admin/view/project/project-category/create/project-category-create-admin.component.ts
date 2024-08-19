import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';

import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




import {ProjectCategoryAdminService} from 'src/app/shared/service/admin/project/ProjectCategoryAdmin.service';
import {ProjectCategoryDto} from 'src/app/shared/model/project/ProjectCategory.model';
import {ProjectCategoryCriteria} from 'src/app/shared/criteria/project/ProjectCategoryCriteria.model';
@Component({
  selector: 'app-project-category-create-admin',
  templateUrl: './project-category-create-admin.component.html'
})
export class ProjectCategoryCreateAdminComponent  implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;



   private _validProjectCategoryCode = true;
   private _validProjectCategoryLibelle = true;

	constructor(private service: ProjectCategoryAdminService , @Inject(PLATFORM_ID) private platformId? ) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
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
                this.item = new ProjectCategoryDto();
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





    public  setValidation(value: boolean){
        this.validProjectCategoryCode = value;
        this.validProjectCategoryLibelle = value;
    }



    public  validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateProjectCategoryCode();
        this.validateProjectCategoryLibelle();
    }

    public validateProjectCategoryCode(){
        if (this.stringUtilService.isEmpty(this.item.code)) {
        this.errorMessages.push('Code non valide');
        this.validProjectCategoryCode = false;
        } else {
            this.validProjectCategoryCode = true;
        }
    }
    public validateProjectCategoryLibelle(){
        if (this.stringUtilService.isEmpty(this.item.libelle)) {
        this.errorMessages.push('Libelle non valide');
        this.validProjectCategoryLibelle = false;
        } else {
            this.validProjectCategoryLibelle = true;
        }
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



    get items(): Array<ProjectCategoryDto> {
        return this.service.items;
    }

    set items(value: Array<ProjectCategoryDto>) {
        this.service.items = value;
    }

    get item(): ProjectCategoryDto {
        return this.service.item;
    }

    set item(value: ProjectCategoryDto) {
        this.service.item = value;
    }

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): ProjectCategoryCriteria {
        return this.service.criteria;
    }

    set criteria(value: ProjectCategoryCriteria) {
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
