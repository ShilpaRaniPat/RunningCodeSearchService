import { Component, OnInit } from '@angular/core';
import { TransferServiceService } from '../transfer-service.service';

@Component({
  selector: 'app-parent',
  templateUrl: './parent.component.html',
  styleUrls: ['./parent.component.css']
})
export class ParentComponent implements OnInit {

  variable;

  constructor(private trans:TransferServiceService) {
    this.trans.GetSearchResult().subscribe((data)=>{
      this.variable=data;
    })
  }

  ngOnInit(){}
}
