import { Component, OnInit , Input} from '@angular/core';
import { TransferServiceService } from '../transfer-service.service';


@Component({
  selector: 'app-my-questions',
  templateUrl: './my-questions.component.html',
  styleUrls: ['./my-questions.component.css']
})
export class MyQuestionsComponent implements OnInit {
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
