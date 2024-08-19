import { OnInit } from '@angular/core';
import { Component } from '@angular/core';
import { LayoutService } from './service/app.layout.service';
import {RoleService} from "../zynerator/security/shared/service/Role.service";
import {AppComponent} from "../app.component";
import {AuthService} from "../zynerator/security/shared/service/Auth.service";
import {Router} from "@angular/router";
import {AppLayoutComponent} from "./app.layout.component";

@Component({
  selector: 'app-menu',
  templateUrl: './app.menu.component.html'
})
export class AppMenuComponent implements OnInit {
  model: any[];
  modelanonymous: any[];
    modelAdmin: any[];
constructor(public layoutService: LayoutService, public app: AppComponent, public appMain: AppLayoutComponent, private roleService: RoleService, private authService: AuthService, private router: Router) { }
  ngOnInit() {
    this.modelAdmin =
      [

				{
                    label: 'Pages',
                    icon: 'pi pi-fw pi-briefcase',
                    items: [
					  {
						label: 'Role Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste role',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/role/role/list']
								  },
						]
					  },
					  {
						label: 'Project Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste project team',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/project/project-team/list']
								  },
								  {
									label: 'Liste project state',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/project/project-state/list']
								  },
								  {
									label: 'Liste project type',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/project/project-type/list']
								  },
								  {
									label: 'Liste project',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/project/project/list']
								  },
								  {
									label: 'Liste project category',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/project/project-category/list']
								  },
						]
					  },
					  {
						label: 'Member Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste member',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/member/member/list']
								  },
						]
					  },
					  {
						label: 'Task Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste task state',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/task/task-state/list']
								  },
								  {
									label: 'Liste task',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/task/task/list']
								  },
						]
					  },

				   {
					  label: 'Security Management',
					  icon: 'pi pi-wallet',
					  items: [
						  {
							  label: 'List User',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/user/list']
						  },
						  {
							  label: 'List Model',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/model-permission/list']
						  },
						  {
							  label: 'List Action Permission',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/action-permission/list']
						  },
					  ]
				  }
			]
	    }
    ];

        if (this.authService.authenticated) {
            if (this.authService.authenticatedUser.roleUsers) {
              this.authService.authenticatedUser.roleUsers.forEach(role => {
                  const roleName: string = this.getRole(role.role.authority);
                  this.roleService._role.next(roleName.toUpperCase());
                  eval('this.model = this.model' + this.getRole(role.role.authority));
              })
            }
        }
  }

    getRole(text){
        const [role, ...rest] = text.split('_');
        return this.upperCaseFirstLetter(rest.join(''));
    }

    upperCaseFirstLetter(word: string) {
      if (!word) { return word; }
      return word[0].toUpperCase() + word.substr(1).toLowerCase();
    }

    onMenuClick(event) {
        this.appMain.onMenuClick(event);
    }
}
