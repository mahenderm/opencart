/* tslint:disable */
import { Injectable } from '@angular/core';

@Injectable()
export class TextMaskService {

    public maskForUsaPhone = ['(', /[1-9]/, /\d/, /\d/, ')', ' ', /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/];
    public maskForPhoneWithPlusOne = ['+','1',' ','(', /[1-9]/, /\d/, /\d/, ')', ' ', /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/];
    public maskForUsaZip = [/[1-9]/, /\d/, /\d/, /\d/, /\d/];
    public maskForCanadaZip = [/[1-9]/, /\d/, /\d/, /\d/, /\d/,'-',/\d/, /\d/, /\d/, /\d/, /\d/];
    public maskForPhoneExtn = [/[1-9]/, /\d/, /\d/, /\d/];

    getPhoneNumberMaskFormat(){
        return this.maskForUsaPhone;
    }

    getMaskForUsaZip(){
        return this.maskForUsaZip;
    }

    getPhoneNumberExtnMaskFormat(){
        return this.maskForPhoneExtn;
    }
    
}
