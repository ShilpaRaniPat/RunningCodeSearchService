import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AcceptedQuesComponent } from './accepted-ques.component';

describe('AcceptedQuesComponent', () => {
  let component: AcceptedQuesComponent;
  let fixture: ComponentFixture<AcceptedQuesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AcceptedQuesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AcceptedQuesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
