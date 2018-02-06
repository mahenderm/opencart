import { NgModule, ApplicationRef } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { BreadcrumbComponent } from '../common/breadcrumb/breadcrumb.component';
import { ControlMessagesComponent }     from '../common/validations/control-messages.component';
import { DialogComponent } from '../common/popup/dialog.component';

@NgModule({
  imports:      [ CommonModule,
                  RouterModule],
  declarations: [ BreadcrumbComponent, 
                  ControlMessagesComponent,
                  DialogComponent],
  exports:      [ BreadcrumbComponent, 
                  ControlMessagesComponent,
                  DialogComponent,
                  CommonModule,
                  RouterModule,
                  FormsModule]
})

/**
 * Shared Module - Used to import reusable component's for different modules
 * eg. BreadcrumbComponent and ControlMessagesComponent
 * import shared module in corresponding module and use the component
 */
export class SharedModule { }
