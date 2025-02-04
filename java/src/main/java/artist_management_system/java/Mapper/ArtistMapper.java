package artist_management_system.java.Mapper;

import artist_management_system.java.Model.ArtistEntity;
import artist_management_system.java.Utils.Enum.Gender;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ArtistMapper {

    public RowMapper<ArtistEntity> getArtistEntityMapper() {
        return (rs, rowNum) -> {
            ArtistEntity artist = new ArtistEntity();
            artist.setId(rs.getInt("id"));
            artist.setDob(rs.getDate("dob"));
            artist.setAddress(rs.getString("address"));
            artist.setGender(rs.getString("gender") != null ? Gender.valueOf(rs.getString("gender")) : null);
            artist.setFirstReleaseYear(rs.get("first_release_year"));
            artist.setNoOfAlbumsReleased(rs.getInt("no_of_albums_released"));
            return artist;
        };
    }
}
