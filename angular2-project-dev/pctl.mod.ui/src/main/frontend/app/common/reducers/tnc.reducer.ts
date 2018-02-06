import { ActionReducer, Action } from '@ngrx/store';
import { TnCList, TnCInfo } from '../models/tnc.model';


export const tncReducer: ActionReducer<TnCList>
    = (state: any = [], action: Action) => {
        switch (action.type) {
            case 'ADD_TNCINFO':
                let add = true;
                state = Object.assign({}, state, {
                    items: (state.items !== undefined) ? state.items.map(item => {
                        if (item._id === action.payload._id) {
                            add = false; // This is already present
                            // update locations if there are any present
                            return Object.assign({}, item, action.payload);
                        }
                        return item;
                    }): []
                });
                if (add) { // new line item to be added
                     if (state.items === undefined || state.items.length === 0) {
                        return Object.assign({}, state, { items: [action.payload] });
                    }
                    return Object.assign({}, state, {
                        items: [...state.items, action.payload]
                    });
                }
                return state;
            default:
                return state;
        }
    };
