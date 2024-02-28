import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenuCreateComponent } from './menuCreate.component';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


export const routes: Routes =[
  {
    path:'',
    component: MenuCreateComponent,
  },
]

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forChild(routes),
  ],
  declarations: [MenuCreateComponent]
})
export class MenuCreateModule { }
