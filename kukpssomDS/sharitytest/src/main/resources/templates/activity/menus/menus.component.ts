import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { MenuEntity } from 'src/app/entity/menu.entity';
import { MenuBookingService } from 'src/app/services/MenuBooking.service';
import { MenuService } from 'src/app/services/menu.service';

@Component({
  selector: 'app-menus',
  templateUrl: './menus.component.html',
  styleUrls: ['./menus.component.scss']
})
export class MenusComponent implements OnInit {

  public menus:MenuEntity[] = []

  @Output() remove: EventEmitter<string> = new EventEmitter()

  constructor(private menuService : MenuService) { 
    this.getMenu();
  }

  public getMenu(){
    this.menuService.getMenu().subscribe(response =>{
      console.log(response);
      this.menus = response
    })
  }

  public onRemove(menu : MenuEntity){
    console.log(menu.menuId)
    this.menuService.deleteMenu(menu.menuId).subscribe(response => {
      this.getMenu()

    })
  }


  ngOnInit() {
  }

}
