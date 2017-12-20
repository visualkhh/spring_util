import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  birthday = null;
  constructor() {
  }

  ngOnInit() {
    this.birthday = new Date(1988, 3, 15); // April 15, 1988
    //debugger;
  }
  ggg(){
    return "aaaaaaaa"+new Date().getTime();
  }

}
