import { Component, OnInit } from '@angular/core';
import { TransferServiceService } from '../transfer-service.service';



@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  something;
  value;
  User;

  constructor(private trans:TransferServiceService) {
    this.trans.GetUserData().subscribe((data) => {
      console.log(data);
      this.User = data;
    });
  }

  ngOnInit() {
    this.value='My Questions';
  }

  showQuestions(){
    this.value='My Questions';
  }

  showAnswers(){
    this.value='My Answers';
  }

  showFollowers(){
    this.value='My Followers'
  }
  
}
