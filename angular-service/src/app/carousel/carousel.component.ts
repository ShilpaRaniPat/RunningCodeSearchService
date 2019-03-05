import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.css'],
})
export class CarouselComponent implements OnInit {



  ngOnInit() {
  }

  items: Array<any> = []

  constructor() {
    this.items = [
      { name: '../../assets/images/t1.jpeg' },
      { name: '../../assets/images/t2.jpeg' },
      { name: '../../assets/images/t3.jpeg' },
      { name: '../../assets/images/t3.jpeg' },
      { name: '../../assets/images/t1.jpeg' },
      { name: '../../assets/images/t2.jpeg' },
      { name: '../../assets/images/t3.jpeg' },
      { name: '../../assets/images/t3.jpeg' },
    ]
  }
}
