import { Component, OnInit } from '@angular/core';
import { AuthService } from '../common/service/auth.service';
import { User } from '../common/models/user.model';
import { Observable } from 'rxjs/Rx';
import { Store } from '@ngrx/store';
import { AppStore } from '../common/models/appstore.model';
import { Breadcrumb } from '../common/models/breadcrumb.model';

@Component({
  selector: 'user-profile',
  styleUrls: ['./user-profile.component.css'],
  templateUrl: './user-profile.component.html'
})
export class UserProfileComponent implements OnInit{
  userDetails: Observable<User>;

  constructor(public store: Store<AppStore>, private authService:AuthService) {
    this.userDetails = authService.user;
  }

  ngOnInit(){
    let breadcrumbs: Breadcrumb[];
         breadcrumbs = [{
            isRoute: false,
            name: 'User Profile',
            routerLink: '/user-profile'
        }];
     this.store.dispatch({ type: 'UPDATE_BREADCRUMB', payload: breadcrumbs });
  }
  
}
