import { ComponentFixture, TestBed } from '@angular/core/testing';

import { commentListComponent } from './commentList.component';

describe('commentListComponent', () => {
  let component: commentListComponent;
  let fixture: ComponentFixture<commentListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ commentListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(commentListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
