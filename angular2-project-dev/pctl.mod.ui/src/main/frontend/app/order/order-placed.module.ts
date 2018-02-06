import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { OrderPlacedComponent }   from './order-placed.component';
import { SharedModule } from '../shared/shared.module';
import { AccordionModule } from '../common/custom-components/accordion/accordion.module';
import { orderPlacedRouting } from './order-placed.routes';

@NgModule({
    imports: [FormsModule,
        ReactiveFormsModule,
        AccordionModule,
        CommonModule,
        SharedModule,
        orderPlacedRouting],
    exports: [
        OrderPlacedComponent],
    declarations: [OrderPlacedComponent],
    providers: []
})
export class OrderPlacedModule { }
