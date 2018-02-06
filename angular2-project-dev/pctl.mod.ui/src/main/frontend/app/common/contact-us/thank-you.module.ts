import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ThankYouComponent } from './thank-you.component';
import { SharedModule } from '../../shared/shared.module';
import { TextMaskModule } from 'angular2-text-mask';
import { TextMaskService } from '../service/text-mask.service';
import { thankYouComponentRouting } from './thank-you.routes';

@NgModule({
    imports: [FormsModule,
        CommonModule,
        ReactiveFormsModule,
        RouterModule,
        SharedModule,
        TextMaskModule,
        thankYouComponentRouting],
    exports: [ThankYouComponent],
    declarations: [ThankYouComponent],
    providers: [TextMaskService]
})

export class ThankYouModule { }
