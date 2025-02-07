package artist_management_system.java.Repository.Impl;

import artist_management_system.java.Mapper.ArtistMapper;
import artist_management_system.java.Model.ArtistEntity;
import artist_management_system.java.Repository.BaseRepository.Impl.BaseRepositoryImpl;
import artist_management_system.java.Repository.IArtistRepository;
import artist_management_system.java.Utils.Enum.Gender;
import jakarta.persistence.Table;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
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
                artist.getFirstReleaseYear() != null ? artist.getFirstReleaseYear().getValue() : null,
                artist.getNoOfAlbumsReleased(),
                new java.util.Date(),

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
        String rawQuery = "UPDATE " + tableName + " SET name = ?, dob = ?, gender = COALESCE(?::gender_enum, gender), address = ?, first_release_year = ?, no_of_albums_released = ?, updated_at = ?"
                + " WHERE id = ?";
        int result = update(rawQuery, new Object[]{
                artist.getName(),
                artist.getDob(),
                artist.getGender() != null ? artist.getGender().name() : null,
                artist.getAddress(),
                artist.getFirstReleaseYear() != null ? artist.getFirstReleaseYear().getValue() : null,
                artist.getNoOfAlbumsReleased(),
                new java.util.Date(),
                artist.getId()
        });
        return result != 0;
    }

    @Override
    public boolean deleteById(Integer id) {
        String rawQuery = "DELETE FROM " + tableName + " WHERE id = ?";
        int result = update(rawQuery, new Object[]{id});

        return result != 0;
    }

    @Override
    public List<ArtistEntity> findAllByPagination(int limit, int offset) {
        String rawQuery = "SELECT * FROM " + tableName + " ORDER BY name LIMIT ? OFFSET ?";

        return query(rawQuery, new Object[]{limit, offset}, artistMapper.getArtistEntityMapper());
    }

    @Override
    public List<ArtistEntity> findAll() {
        String rawQuery = "SELECT * FROM " + tableName + " ORDER BY name";

        return query(rawQuery, new Object[]{}, artistMapper.getArtistEntityMapper());
    }

    @Override
    public int findAllCount() {
        String rawQuery = "SELECT COUNT(*) FROM " + tableName;
        List<ArtistEntity> artistEntityList = query(rawQuery, new Object[]{}, artistMapper.getArtistEntityMapper());
        return artistEntityList.size();
    }

    @Override
    public int[] saveAllCSV(CSVParser artistEntityList) {
        String rawQuery = "INSERT INTO " + tableName + " (name, dob, gender, address, first_release_year, no_of_albums_released, " +
                "created_at) VALUES (?, ?, ?::gender_enum, ?, ?, ?, ?)";
        System.err.println(artistEntityList);
        List<Object[]> batchArgs = new ArrayList<>();
        for (CSVRecord artist : artistEntityList) {
            batchArgs.add(new Object[]{
                    artist.get("name"),
                    Date.valueOf(artist.get("dob")),
                    artist.get("gender") != null ? Gender.valueOf(artist.get("gender")) : null,
                    artist.get("address"),
                    artist.get("first_release_year"),
                    artist.get("no_of_albums_released"),
                    new java.util.Date(),
            });
        }

        return batchUpdate(rawQuery, batchArgs);
    }
}
