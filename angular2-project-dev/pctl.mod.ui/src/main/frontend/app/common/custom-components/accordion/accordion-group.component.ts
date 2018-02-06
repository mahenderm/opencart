import {
  Component, HostBinding, Inject, Input, Output, EventEmitter, OnDestroy, OnInit
} from '@angular/core';
import { AccordionComponent } from './accordion.component';



/* tslint:disable:component-selector-name */
@Component({
  selector: 'accordion-group, accordion-panel',
  template: `
          <div (click)="toggleOpen($event)">
                <ng-content select="[accordion-heading]"></ng-content>
          </div>
          <ng-content select="[accordion-heading-inline]"></ng-content>
          <div [collapse]="!isOpen">
              <ng-content></ng-content>
          </div>
  `
})
export class AccordionPanelComponent implements OnInit, OnDestroy {  
  
  @Input()
  public get isOpen():boolean {
    return this._isOpen;
  }

  public set isOpen(value:boolean) {
    this._isOpen = value;
    if (value) {
      this.accordion.closeOtherPanels(this);
    }
  }

  protected _isOpen:boolean;
  protected accordion:AccordionComponent;

  public constructor(@Inject(AccordionComponent) accordion:AccordionComponent) {
    this.accordion = accordion;
  }

  public ngOnInit():any {
    this.accordion.addGroup(this);
  }

  public ngOnDestroy():any {
    this.accordion.removeGroup(this);
  }

  public toggleOpen() {
    this.isOpen = !this.isOpen;
  }
  
}