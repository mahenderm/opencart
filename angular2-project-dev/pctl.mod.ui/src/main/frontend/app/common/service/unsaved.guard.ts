import { CanDeactivate } from '@angular/router';
import { FormGroup } from '@angular/forms';

export interface FormComponent {
    hasUnsavedChanges(): Boolean;
}

export class UnsavedGuard implements CanDeactivate<FormComponent>{
   
    canDeactivate(component: FormComponent){
        if (component.hasUnsavedChanges())
            return confirm("There are unsaved changes , do you want to continue?");
        return true;
    }
    
}