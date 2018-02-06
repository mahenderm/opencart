import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login.component';
import { SignUpComponent } from './sign-up.component';
import { UserProfileComponent } from './user-profile.component';
import { userProfileComponentRouting } from './user-profile.routes';
import { SharedModule } from '../shared/shared.module';
@NgModule({
    imports: [ FormsModule,
    ReactiveFormsModule,
    CommonModule,
    userProfileComponentRouting,
    SharedModule],
    exports: [ UserProfileComponent ],
    declarations: [ UserProfileComponent,  ],
    providers: [],
})
export class UserProfileModule {

}
