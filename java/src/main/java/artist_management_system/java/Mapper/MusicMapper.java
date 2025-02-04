package artist_management_system.java.Mapper;

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
            music.getArtist().setId(rs.getInt("artist_id"));
            music.setTitle(rs.getString("title"));
            music.setAlbumName(rs.getString("album_name"));
            music.setGenre(rs.getString("genre") != null ? Genre.valueOf(rs.getString("genre")) : null);
            return music;
        };
    }
}
