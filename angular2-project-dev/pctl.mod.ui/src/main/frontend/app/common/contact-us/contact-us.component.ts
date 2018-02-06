import { Component, OnInit } from '@angular/core';
import {
  FormGroup,
  FormControl,
  Validators,
  FormBuilder,
  ReactiveFormsModule
} from '@angular/forms';
import { ContactUs } from './../models/contact-us.model';
import { ContactService } from '../service/contact.service';
import { Observable } from 'rxjs/Observable';
import { Store } from '@ngrx/store';
import { AppStore } from './../models/appstore.model';
import { User } from '../models/user.model';
import { SharedModule } from '../../shared/shared.module';
import { TextMaskService } from '../service/text-mask.service';
import { Validations } from '../validations/validations';
import { Logger } from '../logging/default-log.service';
import { Router } from '@angular/router';
import { BillingApiResponse } from './../models/billing.model';
import { AuthService } from '../service/auth.service';
import { FormComponent } from '../service/unsaved.guard';

/**
 * ContactUs Component
 * If user choose CTL transport, product details will show
 */
@Component({
  selector: 'contact-us',
  templateUrl: './contact-us.component.html'
})

export class ContactUsComponent implements OnInit, FormComponent {
  form: FormGroup;
  user: Observable<User>;
  phoneMask: any;
  billingResponse: BillingApiResponse;
  billingValid: boolean;
  phoneExtnMask: any;
  saving: boolean;

  constructor(
    public contactService: ContactService,
    private formBuilder: FormBuilder,
    public store: Store<AppStore>,
    private textMask: TextMaskService,
    private logger: Logger,
    private router: Router,
    private authService: AuthService) {
    this.user = <Observable<User>>store.select('user');
    this.form = this.formBuilder.group({
      firstName: ['', [<any>Validators.required,
      <any>Validators.minLength(3),
      <any>Validations.nameValidator]],
      lastName: ['', [<any>Validators.required,
      <any>Validations.nameValidator]],
      primaryPhone: ['', [<any>Validators.required,
      <any>Validations.phoneValidator]],
      primaryPhoneExtn: [''],
      email: ['', [<any>Validators.required,
      <any>Validations.emailValidator]],
      companyName: [''],
      jobTitle: [''],
      comments: ['']
    });

    this.phoneMask = this.textMask.getPhoneNumberMaskFormat();
    this.phoneExtnMask = this.textMask.getPhoneNumberExtnMaskFormat();
    this.saving = false;
  }

  ngOnInit() {
    document.body.scrollTop = 0;
  }

  /**
   * ContactUs submit
   */
  contactUs() {
    let contact: ContactUs;
    contact = {
      firstName: this.form.value.firstName,
      lastName: this.form.value.lastName,
      primaryPhone: this.form.value.primaryPhone,
      primaryPhoneExtn: this.form.value.primaryPhoneExtn,
      email: this.form.value.email,
      companyName: this.form.value.companyName,
      jobTitle: this.form.value.jobTitle,
      comments: this.form.value.comments
    };
    this.saving = true;
    let isNewCustomer = this.authService.isNewCustomer();
    this.router.navigate(['/thanks']);
  /*  this.contactService.createLead(contact, isNewCustomer)
      .map(payload => payload)
      .catch((error: any) => {
        return Observable.throw(error._body);
      })
      .subscribe(
      data => {
        //this.logger.log("data " + JSON.stringify(data))
        this.billingValid = true;
        this.billingResponse = data;

        // TODO: based on ContactApiResponse page redirect to thanks page else remain here 
        // still Contact API not yet complete so else condition process it
        if (this.billingResponse.message === 'Success'
          && this.billingResponse.code === '200') {
          this.router.navigate(['/thanks']);
        } else {
          this.router.navigate(['/thanks']);
        }
      },
      err => {
        this.logger.error("Error contactus page " + JSON.parse(err));
        this.billingValid = true;
        this.billingResponse = JSON.parse(err);
        this.router.navigate(['/thanks']);
      });*/
  } 

    hasUnsavedChanges(): Boolean {
        return this.form.dirty && !this.saving;
    }
}
