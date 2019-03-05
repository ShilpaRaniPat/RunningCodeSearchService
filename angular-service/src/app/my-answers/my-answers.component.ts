import { Component, OnInit, Input } from '@angular/core';
import { TransferServiceService } from '../transfer-service.service';

@Component({
  selector: 'app-my-answers',
  templateUrl: './my-answers.component.html',
  styleUrls: ['./my-answers.component.css']
})
export class MyAnswersComponent implements OnInit {

  @Input()
  in;

  constructor(private trans:TransferServiceService) { }

  ngOnInit() {
  }

  getOtherDataModel(){
    console.log("in");
    //Qadm=get req;
    //this.trans.value=this.Qadm;
  }
}
