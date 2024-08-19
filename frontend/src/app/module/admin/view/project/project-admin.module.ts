import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {ToastModule} from 'primeng/toast';
import {ToolbarModule} from 'primeng/toolbar';
import {TableModule} from 'primeng/table';
import {DropdownModule} from 'primeng/dropdown';
import {InputSwitchModule} from 'primeng/inputswitch';
import {InputTextareaModule} from 'primeng/inputtextarea';
import {EditorModule} from "primeng/editor";

import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { DialogModule } from 'primeng/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {CalendarModule} from 'primeng/calendar';
import {PanelModule} from 'primeng/panel';
import {InputNumberModule} from 'primeng/inputnumber';
import {BadgeModule} from 'primeng/badge';
import { MultiSelectModule } from 'primeng/multiselect';
import {TranslateModule} from '@ngx-translate/core';
import {FileUploadModule} from 'primeng/fileupload';
import {FullCalendarModule} from '@fullcalendar/angular';
import {CardModule} from "primeng/card";
import {TagModule} from "primeng/tag";

import { ProjectTeamCreateAdminComponent } from './project-team/create/project-team-create-admin.component';
import { ProjectTeamEditAdminComponent } from './project-team/edit/project-team-edit-admin.component';
import { ProjectTeamViewAdminComponent } from './project-team/view/project-team-view-admin.component';
import { ProjectTeamListAdminComponent } from './project-team/list/project-team-list-admin.component';
import { ProjectStateCreateAdminComponent } from './project-state/create/project-state-create-admin.component';
import { ProjectStateEditAdminComponent } from './project-state/edit/project-state-edit-admin.component';
import { ProjectStateViewAdminComponent } from './project-state/view/project-state-view-admin.component';
import { ProjectStateListAdminComponent } from './project-state/list/project-state-list-admin.component';
import { ProjectTypeCreateAdminComponent } from './project-type/create/project-type-create-admin.component';
import { ProjectTypeEditAdminComponent } from './project-type/edit/project-type-edit-admin.component';
import { ProjectTypeViewAdminComponent } from './project-type/view/project-type-view-admin.component';
import { ProjectTypeListAdminComponent } from './project-type/list/project-type-list-admin.component';
import { ProjectCreateAdminComponent } from './project/create/project-create-admin.component';
import { ProjectEditAdminComponent } from './project/edit/project-edit-admin.component';
import { ProjectViewAdminComponent } from './project/view/project-view-admin.component';
import { ProjectListAdminComponent } from './project/list/project-list-admin.component';
import { ProjectCategoryCreateAdminComponent } from './project-category/create/project-category-create-admin.component';
import { ProjectCategoryEditAdminComponent } from './project-category/edit/project-category-edit-admin.component';
import { ProjectCategoryViewAdminComponent } from './project-category/view/project-category-view-admin.component';
import { ProjectCategoryListAdminComponent } from './project-category/list/project-category-list-admin.component';

import { PasswordModule } from 'primeng/password';
import { InputTextModule } from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {TabViewModule} from 'primeng/tabview';
import { SplitButtonModule } from 'primeng/splitbutton';
import { MessageModule } from 'primeng/message';
import {MessagesModule} from 'primeng/messages';
import {PaginatorModule} from 'primeng/paginator';



@NgModule({
  declarations: [

    ProjectTeamCreateAdminComponent,
    ProjectTeamListAdminComponent,
    ProjectTeamViewAdminComponent,
    ProjectTeamEditAdminComponent,
    ProjectStateCreateAdminComponent,
    ProjectStateListAdminComponent,
    ProjectStateViewAdminComponent,
    ProjectStateEditAdminComponent,
    ProjectTypeCreateAdminComponent,
    ProjectTypeListAdminComponent,
    ProjectTypeViewAdminComponent,
    ProjectTypeEditAdminComponent,
    ProjectCreateAdminComponent,
    ProjectListAdminComponent,
    ProjectViewAdminComponent,
    ProjectEditAdminComponent,
    ProjectCategoryCreateAdminComponent,
    ProjectCategoryListAdminComponent,
    ProjectCategoryViewAdminComponent,
    ProjectCategoryEditAdminComponent,
  ],
  imports: [
    CommonModule,
    ToastModule,
    ToolbarModule,
    TableModule,
    ConfirmDialogModule,
    DialogModule,
    PasswordModule,
    InputTextModule,
    ButtonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    SplitButtonModule,
    BrowserAnimationsModule,
    DropdownModule,
    TabViewModule,
    InputSwitchModule,
    InputTextareaModule,
    CalendarModule,
    PanelModule,
    MessageModule,
    MessagesModule,
    InputNumberModule,
    BadgeModule,
    MultiSelectModule,
    PaginatorModule,
    TranslateModule,
    FileUploadModule,
    FullCalendarModule,
    CardModule,
    EditorModule,
    TagModule,


  ],
  exports: [
  ProjectTeamCreateAdminComponent,
  ProjectTeamListAdminComponent,
  ProjectTeamViewAdminComponent,
  ProjectTeamEditAdminComponent,
  ProjectStateCreateAdminComponent,
  ProjectStateListAdminComponent,
  ProjectStateViewAdminComponent,
  ProjectStateEditAdminComponent,
  ProjectTypeCreateAdminComponent,
  ProjectTypeListAdminComponent,
  ProjectTypeViewAdminComponent,
  ProjectTypeEditAdminComponent,
  ProjectCreateAdminComponent,
  ProjectListAdminComponent,
  ProjectViewAdminComponent,
  ProjectEditAdminComponent,
  ProjectCategoryCreateAdminComponent,
  ProjectCategoryListAdminComponent,
  ProjectCategoryViewAdminComponent,
  ProjectCategoryEditAdminComponent,
  ],
})
export class ProjectAdminModule { }
