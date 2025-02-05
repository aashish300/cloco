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
        String rawQuery = "UPDATE " + tableName + " SET name = ?, dob = ?, gender = COALESCE(?::gender_enum, gender), address = ?, first_release_year = ?, no_of_albums_released = ?, updated_at = ?"
                + " WHERE id = ?";
        int result = update(rawQuery, new Object[]{
                artist.getName(),
                artist.getDob(),
                artist.getGender() != null ? artist.getGender().name() : null,
                artist.getAddress(),
                artist.getFirstReleaseYear() != null ? artist.getFirstReleaseYear().getValue() : null,
                artist.getNoOfAlbumsReleased(),
                artist.getUpdatedAt(),
                artist.getId()
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

    //    public void importCsv(MultipartFile file) {
//        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
//            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
//
//            List<Object[]> batchArgs = new ArrayList<>();
//            for (CSVRecord record : csvParser) {
//                Long id = Long.parseLong(record.get("id"));
//                String name = record.get("name");
//                String genre = record.get("genre");
//
//                batchArgs.add(new Object[]{id, name, genre});
//            }
//
//            // Bulk Insert using JdbcTemplate
//            String sql = "INSERT INTO artists (id, name, genre) VALUES (?, ?, ?)";
//            jdbcTemplate.batchUpdate(sql, batchArgs);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException("Failed to import CSV file: " + e.getMessage());
//        }
//    }
//
//    public void exportCsv(HttpServletResponse response) throws IOException {
//        response.setContentType("text/csv");
//        response.setHeader("Content-Disposition", "attachment; filename=artists.csv");
//
//        PrintWriter writer = response.getWriter();
//        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("id", "name", "genre"));
//
//        // Fetch data from DB
//        List<Map<String, Object>> artists = jdbcTemplate.queryForList("SELECT * FROM artists");
//
//        for (Map<String, Object> artist : artists) {
//            csvPrinter.printRecord(artist.get("id"), artist.get("name"), artist.get("genre"));
//        }
//
//        csvPrinter.flush();
//        csvPrinter.close();
//    }
}
