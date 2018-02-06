import { User } from './user.model';
import { Product } from './product.model';
import { Pricing } from './pricing.model';
import { ActiveStatus } from './pricing.model';
import { ShoppingCart } from './cart.model';
import { TnCList } from './tnc.model';

/**
 * Application Store for state management
 */
export interface AppStore {
  user: User;           // user state/profile
  cart: ShoppingCart;   // user related shopping cart
  product: Product;     // displayed products information
  prices: Pricing[];    // displayed pricing information
  status?: ActiveStatus;  // current product selection information
  tnc: TnCList;
};
