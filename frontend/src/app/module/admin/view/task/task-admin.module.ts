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

import { TaskStateCreateAdminComponent } from './task-state/create/task-state-create-admin.component';
import { TaskStateEditAdminComponent } from './task-state/edit/task-state-edit-admin.component';
import { TaskStateViewAdminComponent } from './task-state/view/task-state-view-admin.component';
import { TaskStateListAdminComponent } from './task-state/list/task-state-list-admin.component';
import { TaskCreateAdminComponent } from './task/create/task-create-admin.component';
import { TaskEditAdminComponent } from './task/edit/task-edit-admin.component';
import { TaskViewAdminComponent } from './task/view/task-view-admin.component';
import { TaskListAdminComponent } from './task/list/task-list-admin.component';

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

    TaskStateCreateAdminComponent,
    TaskStateListAdminComponent,
    TaskStateViewAdminComponent,
    TaskStateEditAdminComponent,
    TaskCreateAdminComponent,
    TaskListAdminComponent,
    TaskViewAdminComponent,
    TaskEditAdminComponent,
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
  TaskStateCreateAdminComponent,
  TaskStateListAdminComponent,
  TaskStateViewAdminComponent,
  TaskStateEditAdminComponent,
  TaskCreateAdminComponent,
  TaskListAdminComponent,
  TaskViewAdminComponent,
  TaskEditAdminComponent,
  ],
})
export class TaskAdminModule { }
