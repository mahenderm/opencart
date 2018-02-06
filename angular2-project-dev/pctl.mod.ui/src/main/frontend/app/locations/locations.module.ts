import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { LocationsComponent } from './locations.component';
import { LocationsBatchUploadComponent } from './locations-batch-upload.component';
import { LocationsListComponent } from './locations-list.component';
import { LocationsFormComponent } from './locations-form.component';
import { locationsRouting } from './locations.routes';
// import { BreadcrumbComponent }          from '../common/breadcrumb/breadcrumb.component';
// import { ControlMessagesComponent }     from '../common/validations/control-messages.component';
// import { Validations }                  from '../common/validations/validations';
import { PaginationModule } from 'ng2-bootstrap/ng2-bootstrap';
import { CtlPaginationComponent } from '../common/pagination/pagination.component';
import { CountryStateService } from '../common/service/country-state.service';
import { LocationsService } from './locations.service';
import { AccordionModule } from '../common/custom-components/accordion/accordion.module';
import { SharedModule } from '../shared/shared.module';
import { TextMaskModule } from 'angular2-text-mask';
import { Ng2AutoCompleteModule } from '../common/custom-components/ng2-auto-complete/';
import { CityService } from '../common/service/city.service';
import { TooltipModule } from 'ng2-bootstrap/ng2-bootstrap';
import { TextMaskService } from '../common/service/text-mask.service';
import { ModalModule } from "ng2-modal";

@NgModule({
    imports: [FormsModule,
        CommonModule,
        ReactiveFormsModule,
        RouterModule,
        PaginationModule,
        AccordionModule,
        SharedModule,
        TextMaskModule,
        Ng2AutoCompleteModule,
        TooltipModule,
        locationsRouting,
        ModalModule],
    exports: [LocationsComponent,
        LocationsBatchUploadComponent,
        LocationsListComponent,
        LocationsFormComponent],
    declarations: [LocationsComponent,
        LocationsBatchUploadComponent,
        LocationsListComponent,
        LocationsFormComponent,
        CtlPaginationComponent],
    providers: [CountryStateService,
        LocationsService,
        CityService,
        TextMaskService]
})

export class LocationsModule { }
