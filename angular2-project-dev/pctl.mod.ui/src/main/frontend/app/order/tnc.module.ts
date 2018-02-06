import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { TnCComponent }   from './tnc.component';
import { SharedModule } from '../shared/shared.module';
import { AccordionModule } from '../common/custom-components/accordion/accordion.module';
import { tncRouting } from './tnc.routes';

@NgModule({
    imports: [FormsModule,
        ReactiveFormsModule,
        AccordionModule,
        CommonModule,
        SharedModule,
        tncRouting],
    exports: [
        TnCComponent],
    declarations: [TnCComponent],
    providers: []
})
export class TnCModule { }
