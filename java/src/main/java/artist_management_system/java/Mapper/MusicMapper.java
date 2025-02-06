package artist_management_system.java.Mapper;

import artist_management_system.java.Model.ArtistEntity;
import artist_management_system.java.Model.MusicEntity;
import artist_management_system.java.Utils.Enum.Genre;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class MusicMapper {

    public RowMapper<MusicEntity> getMusicEntityRowMapper() {
        return (rs, rowNum) -> {
            MusicEntity music = new MusicEntity();
            music.setId(rs.getInt("id"));
            ArtistEntity artist = new ArtistEntity();
            artist.setId(rs.getInt("artist_id"));
            music.setArtist(artist);
            music.setTitle(rs.getString("title"));
            music.setAlbumName(rs.getString("album_name"));
            music.setGenre(rs.getString("genre") != null ? Genre.valueOf(rs.getString("genre")) : null);
            return music;
        };
    }
}
