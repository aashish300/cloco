import {BaseInterface} from "./base.interface";

export interface UserInterface extends BaseInterface {
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  phone: number;
  token: string;
  role: string;
  gender: string;
  dob: Date;
  address: string;
}
