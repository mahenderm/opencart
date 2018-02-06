import {Injectable} from '@angular/core';
import {CITIES} from '../db-static/cities';
import * as _ from 'lodash';

@Injectable()
export class CityService{

    fetchCities():any{
      let data=_.uniq(CITIES);
      return data;
    }

}