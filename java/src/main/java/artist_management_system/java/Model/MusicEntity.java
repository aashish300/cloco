package artist_management_system.java.Model;

import artist_management_system.java.Model.BaseEntity.BaseEntity;
import artist_management_system.java.Utils.Enum.Genre;
import jakarta.persistence.*;

@Entity
@Table(name = "tbl_music")
public class MusicEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private ArtistEntity artist;

    @Column(name = "title")
    private String title;

    @Column(name = "album_name")
    private String albumName;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre", columnDefinition = "genre_enum")
    private Genre genre;

    public ArtistEntity getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public String getAlbumName() {
        return albumName;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setArtist(ArtistEntity artist) {
        this.artist = artist;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
