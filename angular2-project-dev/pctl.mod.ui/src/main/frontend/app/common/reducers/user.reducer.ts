import { ActionReducer, Action } from '@ngrx/store';
import { User } from '../models/user.model';
import { CartState } from '../models/cart.model';
const initialState: User = {
    id: 0,
    email: '',
    loggedIn: false,
    firstName: '',
    lastName: '',
    phoneNumber: '',
    companyName: '',
    einNumber: ''
};

export const userReducer: ActionReducer<User>
              = (state: any = [], action: Action) => {
  switch (action.type) {
    case 'CREATE_USER':
      return Object.assign({}, state, action.payload);
    case 'UPDATE_USER':
      return Object.assign({}, state, action.payload);
    case 'DELETE_USER':
      return Object.assign({}, state, initialState);
    case 'INIT_USER':
      return initialState;
    case 'UPDATE_ENT_DETAILS':
      return Object.assign({}, state, action.payload);
    case 'UPDATE_ACTIVE_SELECTION':
      return Object.assign({}, state, { status : action.payload});
    case 'UPDATE_BREADCRUMB':
      return Object.assign({}, state, { breadcrumbs : action.payload});
    case 'UPDATE_CART_DETAILS':
      return Object.assign({}, state, action.payload);
    case 'ADD_USER_TOKEN':
      return Object.assign({}, state, action.payload);
    case 'ADD_JWT_TOKEN':
      return Object.assign({}, state, action.payload); 
    case 'ADD_FINGERPRINT':
      return Object.assign({}, state, action.payload);
    case 'ADD_BILLING':
      return Object.assign({}, state, { billing : action.payload});
    case 'PREVIOUS_URL':
      return Object.assign({}, state, { previousUrl : action.payload});
    case 'FORCE_USER_CHECK':
      return Object.assign({}, state, { forceCheck : action.payload});
    case 'ADD_PROFILE_INFO':
      return Object.assign({}, state, action.payload);
    case 'GENERAL_AGREEMENT':
      return Object.assign({}, state, { generalTerm : action.payload});
    case 'SDWAN_AGREEMENT':
      return Object.assign({}, state, { sdWanTerm : action.payload});
    default:
      return state;
  }
};
