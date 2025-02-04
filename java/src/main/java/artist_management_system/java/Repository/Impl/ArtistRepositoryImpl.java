package artist_management_system.java.Repository.Impl;

import artist_management_system.java.Mapper.ArtistMapper;
import artist_management_system.java.Model.ArtistEntity;
import artist_management_system.java.Repository.BaseRepository.Impl.BaseRepositoryImpl;
import artist_management_system.java.Repository.IArtistRepository;
import jakarta.persistence.Table;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArtistRepositoryImpl extends BaseRepositoryImpl<ArtistEntity> implements IArtistRepository {

    private final ArtistMapper artistMapper;
    private final String tableName = ArtistEntity.class.getAnnotation(Table.class).name();

    public ArtistRepositoryImpl(JdbcTemplate jdbcTemplate, ArtistMapper artistMapper) {
        super(jdbcTemplate);
        this.artistMapper = artistMapper;
    }

    @Override
    public boolean save(ArtistEntity artist) {
        String rawQuery = "INSERT INTO " + tableName + " (name, dob, gender, address, first_release_year, no_of_albums_released, " +
                "created_at) VALUES (?, ?, ?::gender_enum, ?, ?, ?, ?)";
        int result = update(rawQuery, new Object[]{
                artist.getName(),
                artist.getDob(),
                artist.getGender() != null ? artist.getGender().name() : null,
                artist.getAddress(),
                artist.getFirstReleaseYear(),
                artist.getNoOfAlbumsReleased(),
                artist.getCreatedAt(),
        });
        return result != 0;
    }

    @Override
    public ArtistEntity findById(Integer id) {
        String rawQuery = "SELECT * FROM " + tableName + " WHERE id = ?";

        return queryForObject(rawQuery, new Object[]{id}, artistMapper.getArtistEntityMapper());
    }

    @Override
    public boolean update(ArtistEntity artist) {
        String rawQuery = "UPDATE " + tableName + " SET name = ?, dob = ?, gender = ?::gender_enum, address = ?, first_release_year = ?, no_of_albums_released = ?, updated_at = ?"
                + " WHERE id = ?";
        int result = update(rawQuery, new Object[]{
                artist.getName(),
                artist.getDob(),
                artist.getGender() != null ? artist.getGender().name() : null,
                artist.getAddress(),
                artist.getNoOfAlbumsReleased(),
                artist.getFirstReleaseYear(),
                artist.getUpdatedAt()
        });
        return result != 0;
    }

    @Override
    public boolean deleteById(Integer id) {
        String rawQuery = "DELETE " + tableName + " WHERE id = ?";
        int result = update(rawQuery, new Object[]{id});

        return result != 0;
    }

    @Override
    public List<ArtistEntity> findAllByPagination(int limit, int offset) {
        String rawQuery = "SELECT * FROM " + tableName + " ORDER BY name LIMIT = ? OFFSET = ?";

        return query(rawQuery, new Object[]{limit, offset}, artistMapper.getArtistEntityMapper());
    }

    @Override
    public List<ArtistEntity> findAll() {
        String rawQuery = "SELECT * FROM " + tableName + " ORDER BY name";

        return query(rawQuery, new Object[]{}, artistMapper.getArtistEntityMapper());
    }
}
