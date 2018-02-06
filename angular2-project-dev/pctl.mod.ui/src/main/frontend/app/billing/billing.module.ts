import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { BillingComponent } from './billing.component';
import { SharedModule } from '../shared/shared.module';
import { OrderService } from '../order/order.service';
import { billingComponentRouting } from './billing.routes';
import { TextMaskModule } from 'angular2-text-mask';

@NgModule({
    imports: [FormsModule,
        CommonModule,
        ReactiveFormsModule,
        RouterModule,
        SharedModule,
        billingComponentRouting,
        TextMaskModule],
    exports: [BillingComponent],
    declarations: [BillingComponent],
    providers: [ OrderService ]
})
export class BillingModule { }
