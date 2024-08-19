import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import {AuthGuard} from 'src/app/zynerator/security/guards/auth.guard';

import { ActivationAdminComponent } from './activation-admin/activation-admin.component';
import { LoginAdminComponent } from './login-admin/login-admin.component';
import { RegisterAdminComponent } from './register-admin/register-admin.component';
import { ForgetPasswordAdminComponent } from './forget-password-admin/forget-password-admin.component';
import { ChangePasswordAdminComponent } from './change-password-admin/change-password-admin.component';

@NgModule({
    imports: [
        RouterModule.forChild(
            [
                {
                    path: '',
                    children: [
                        {
                            path: 'login',
                            children: [
                                {
                                    path: '',
                                    component: LoginAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'register',
                            children: [
                                {
                                    path: '',
                                    component: RegisterAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },{
                            path: 'forget-password',
                            children: [
                                {
                                    path: '',
                                    component: ForgetPasswordAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },{
                            path: 'change-password',
                            children: [
                                {
                                    path: '',
                                    component: ChangePasswordAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'activation',
                            children: [
                                {
                                    path: '',
                                    component: ActivationAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'role',
                            loadChildren: () => import('./view/role/role-admin-routing.module').then(x => x.RoleAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'task',
                            loadChildren: () => import('./view/task/task-admin-routing.module').then(x => x.TaskAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'project',
                            loadChildren: () => import('./view/project/project-admin-routing.module').then(x => x.ProjectAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'member',
                            loadChildren: () => import('./view/member/member-admin-routing.module').then(x => x.MemberAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'security',
                            loadChildren: () => import('../security/security-routing.module').then(x => x.SecurityRoutingModule),
                            canActivate: [AuthGuard],
                        }
                    ]
                },
            ]
        ),
    ],
    exports: [RouterModule],
})
export class AdminRoutingModule { }
