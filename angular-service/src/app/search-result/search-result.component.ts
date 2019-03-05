import { Component, OnInit } from '@angular/core';
import { TransferServiceService } from '../transfer-service.service';

@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrls: ['./search-result.component.css']
})
export class SearchResultComponent implements OnInit {

  resultValue;

  constructor(private trans:TransferServiceService) { }

  ngOnInit() {
    this.resultValue=this.trans.searchValue;
  }

}
