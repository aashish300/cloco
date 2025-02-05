import {BaseInterface} from "./base.interface";

export interface ArtistInterface extends BaseInterface {
  name: string;
  dob: Date;
  gender: string;
  address: string;
  firstReleaseYear: number;
  noOfAlbumsReleased: number;
}
