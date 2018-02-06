import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ContactUsComponent } from './contact-us.component';
import { SharedModule } from '../../shared/shared.module';
import { TextMaskModule } from 'angular2-text-mask';
import { TextMaskService } from '../service/text-mask.service';
import { contactUsComponentRouting } from './contact-us.routes';

@NgModule({
    imports: [FormsModule,
        CommonModule,
        ReactiveFormsModule,
        RouterModule,
        SharedModule,
        TextMaskModule,
        contactUsComponentRouting],
    exports: [ContactUsComponent],
    declarations: [ContactUsComponent],
    providers: [TextMaskService]
})

export class ContactUsModule { }
