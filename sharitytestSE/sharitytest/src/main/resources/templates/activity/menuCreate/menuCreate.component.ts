import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormGroupDirective, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MenuEntity } from 'src/app/entity/menu.entity';
import { MenuService } from 'src/app/services/menu.service';

@Component({
  selector: 'app-menuCreate',
  templateUrl: './menuCreate.component.html',
  styleUrls: ['./menuCreate.component.scss']
})
export class MenuCreateComponent implements OnInit {

  public form: FormGroup = new FormGroup({
    name: new FormControl(null, [Validators.required]),
    price: new FormControl(null, [Validators.required]),
    type: new FormControl(null, [Validators.required]),
    imgPath: new FormControl(null, [Validators.required]),
  })

  constructor(private menuService : MenuService,private router : Router) { }

  public submit(form: FormGroupDirective) {
      console.log(form.value)
      const data = form.value
      let model: MenuEntity = {
        menuId: undefined,
        name: data.name,
        price: data.price,
        type: data.type,
        imgPath: data.imgPath
      }
      this.menuService.addMenu(model).subscribe(response => {
        this.router.navigateByUrl('/menu')
      })
  }

  ngOnInit() {
  }

}
