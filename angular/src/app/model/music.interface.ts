export interface MusicInterface {
  artist_id: number;
  title: string;
  album_name: string;
  genre: Genre
}

export enum Genre {
  rnb,
  country,
  classic,
  rock,
  jazz
}
