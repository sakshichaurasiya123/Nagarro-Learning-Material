import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowAllQuestionsComponent } from './show-all-questions.component';

describe('ShowAllQuestionsComponent', () => {
  let component: ShowAllQuestionsComponent;
  let fixture: ComponentFixture<ShowAllQuestionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowAllQuestionsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowAllQuestionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
