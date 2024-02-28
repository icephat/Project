import { Component, Input, OnInit } from '@angular/core';
import { MenuBookingEntity } from 'src/app/entity/menubooking.entity';

@Component({
  selector: 'app-menubookingsList',
  templateUrl: './menubookingsList.component.html',
  styleUrls: ['./menubookingsList.component.scss']
})
export class MenubookingsListComponent implements OnInit {

  @Input() booking? : MenuBookingEntity

  constructor() { }

  ngOnInit() {
  }

}
