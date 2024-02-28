import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenubookingsCreateComponent } from './menubookingsCreate.component';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

export const routes: Routes =[
  {
    path:'',
    component: MenubookingsCreateComponent,
  },
]

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forChild(routes),
  ],
  declarations: [MenubookingsCreateComponent]
})
export class MenubookingsCreateModule { }
