package artist_management_system.java.Repository.Impl;

import artist_management_system.java.Mapper.MusicMapper;
import artist_management_system.java.Model.MusicEntity;
import artist_management_system.java.Repository.BaseRepository.Impl.BaseRepositoryImpl;
import artist_management_system.java.Repository.IMusicRepository;
import jakarta.persistence.Table;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MusicRepositoryImpl extends BaseRepositoryImpl<MusicEntity> implements IMusicRepository {

    private final MusicMapper musicMapper;
    private final String tableName = MusicEntity.class.getAnnotation(Table.class).name();

    public MusicRepositoryImpl(JdbcTemplate jdbcTemplate, MusicMapper musicMapper) {
        super(jdbcTemplate);
        this.musicMapper = musicMapper;
    }

    @Override
    public boolean save(MusicEntity music) {
        String rawQuery = "INSERT INTO " + tableName + " (artist_id, title, album_name, genre, created_at)" +
                " VALUES (?, ?, ?, ?::genre_enum, ?)";
        int result = update(rawQuery, new Object[]{
                music.getArtist().getId(),
                music.getTitle(),
                music.getAlbumName(),
                music.getGenre() != null ? music.getGenre().name() : null,
                music.getCreatedAt()
        });
        return result != 0;
    }

    @Override
    public MusicEntity findById(Integer id) {
        String rawQuery = "SELECT * FROM " + tableName + " WHERE id = ?";

        return queryForObject(rawQuery, new Object[]{id}, musicMapper.getMusicEntityRowMapper());
    }

    @Override
    public boolean update(MusicEntity music) {
        String rawQuery = "UPDATE " + tableName + " SET artist_id = ?, title = ?, album_name = ?, "
                + "genre = ?::genre_enum, updated_at = ? WHERE id = ?";
        int result = update(rawQuery, new Object[]{
                music.getArtist().getId(),
                music.getTitle(),
                music.getAlbumName(),
                music.getGenre() != null ? music.getGenre().name() : null,
                music.getUpdatedAt()
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
    public List<MusicEntity> findAllByPagination(int limit, int offset) {
        String rawQuery = "SELECT * FROM " + tableName + " ORDER BY title LIMIT = ? OFFSET = ?";

        return query(rawQuery, new Object[]{limit, offset}, musicMapper.getMusicEntityRowMapper());
    }

    @Override
    public List<MusicEntity> findAll() {
        String rawQuery = "SELECT * FROM " + tableName + " ORDER BY title";

        return query(rawQuery, new Object[]{}, musicMapper.getMusicEntityRowMapper());
    }

    @Override
    public MusicEntity findByArtistId(Integer artistId) {
        String rawQuery = "SELECT * FROM " + tableName + " WHERE artist_id = ?";

        return queryForObject(rawQuery, new Object[]{artistId}, musicMapper.getMusicEntityRowMapper());
    }
}
