import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { OrderComponent } from './order.component';
import { OrderSummaryDetailComponent }  from './order-summary-detail.component';
// import { OrderPlacedComponent }   from './order-placed.component';
import { SavePdfComponent } from './save-pdf.component';
// import { TnCComponent } from './tnc.component';
import { SharedModule } from '../shared/shared.module';
import { AccordionModule } from '../common/custom-components/accordion/accordion.module';
import { orderSummaryRouting } from './order-summary-detail.routes';
import { ModalModule } from "ng2-modal";

@NgModule({
    imports: [
        FormsModule,
        ReactiveFormsModule,
        AccordionModule,
        CommonModule,
        SharedModule,
        orderSummaryRouting,
        ModalModule
        ],
    exports: [
        OrderComponent,
        OrderSummaryDetailComponent,
        SavePdfComponent
        ],
    declarations: [OrderComponent,
        OrderSummaryDetailComponent,
        SavePdfComponent
        ],
    providers: []
})
export class OrderModule { }
