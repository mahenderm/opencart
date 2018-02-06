import {
    Component,
    ViewChild,
    Input,
    Output,
    EventEmitter,
    ElementRef,
    OnInit
} from '@angular/core';

import { Modal } from 'ng2-modal';

@Component({
    selector: 'modal-dialog',
    templateUrl: './modal.component.html'
})
export class ModalComponent {
    @Input() message: string;
    @Input() title: string;
    @Input() hashTag: string;
    @Input() submitButtonLabel: string;
    @Input() cancelButtonLabel: string;
    @Output() confirmEvent = new EventEmitter();
    @Input() openEvent = new EventEmitter();
    @ViewChild('myModalDialog') modalDialog: Modal;
    result: boolean;

    constructor(private elementRef: ElementRef) {
        if (this.message === undefined)
            this.message = 'Are you sure ?';
        if (this.title === undefined)
            this.title = 'Confirm';
        if (this.hashTag === undefined)
            this.hashTag = 'myModalDialog';
        if (this.submitButtonLabel === undefined)
            this.submitButtonLabel = 'OK';
        if (this.cancelButtonLabel === undefined)
            this.cancelButtonLabel = 'Cancel';
        this.result = false;
    }

    submitHandler() {
        this.modalDialog.close();
        this.result = true;
    }

    closeHandler($event) {
     //   console.log($event);
        this.modalDialog.close();
        this.confirmEvent.emit(this.result);
    }

    @Input() set openForm(open: boolean) {
        if (open)
            this.modalDialog.open();
        else 
            this.modalDialog.close();
    }

}
