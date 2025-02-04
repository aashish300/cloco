import {BaseInterface} from "./base.interface";

export interface ArtistInterface extends BaseInterface {
  name: string;
  dob: Date;
  gender: string;
  address: string;
  first_release_year: number;
  no_of_albums_released: number;
}
