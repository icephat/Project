import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenusComponent } from './menus.component';
import { RouterModule, Routes } from '@angular/router';
import { MenuListModule } from '../menuList/menuList.module';

export const routes: Routes =[
  {
    path:'',
    component: MenusComponent,
  },
]
@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    MenuListModule
  ],
  declarations: [MenusComponent]
})
export class MenusModule { }
