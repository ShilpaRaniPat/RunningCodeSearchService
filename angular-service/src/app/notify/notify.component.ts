import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-notify',
  templateUrl: './notify.component.html',
  styleUrls: ['./notify.component.css']
})
export class NotifyComponent implements OnInit {
  @Input() notification:string;
  constructor(private router :Router) { }

  ngOnInit() {
  }
  

  navigate(){
    //do your any operations
    this.router.navigate(["/notificationCard"]);
  }
  

}
