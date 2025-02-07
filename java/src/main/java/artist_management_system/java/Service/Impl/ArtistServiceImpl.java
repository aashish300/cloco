package artist_management_system.java.Service.Impl;

import artist_management_system.java.Model.ArtistEntity;
import artist_management_system.java.Repository.IArtistRepository;
import artist_management_system.java.Service.IArtistService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import com.opencsv.CSVReader;


@Service
@Transactional
public class ArtistServiceImpl implements IArtistService {

    private final IArtistRepository artistRepository;

    @Autowired
    public ArtistServiceImpl(IArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public ArtistEntity save(ArtistEntity artist) {
        boolean result = artistRepository.save(artist);
        if (result) {
            return artist;
        }
        return null;
    }

    @Override
    public ArtistEntity findById(Integer id) {
        return this.artistRepository.findById(id);
    }

    @Override
    public ArtistEntity update(ArtistEntity artist) {
        boolean result = artistRepository.update(artist);
        if (result) {
            return artist;
        }
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        return this.artistRepository.deleteById(id);
    }

    @Override
    public List<ArtistEntity> findAllByPagination(Integer page, Integer size) {
        int offset = (page - 1) * size;
        return this.artistRepository.findAllByPagination(size, offset);
    }

    @Override
    public List<ArtistEntity> findAll() {
        return this.artistRepository.findAll();
    }

    @Override
    public List<ArtistEntity> saveAll(List<ArtistEntity> artistEntityList) {
        boolean result = artistRepository.saveAll(artistEntityList);
        if (result) {
            return artistEntityList;
        }
        return null;
    }

//    @Override
//    public void exportCSV(HttpServletResponse response) throws IOException {
//        response.setContentType("text/csv");
//        response.addHeader("Content-Disposition", "attachment; filename=\"Artist.csv\"");
//        List<ArtistEntity> artistEntityList = this.artistRepository.findAll();
//        Writer writer = response.getWriter();
//        try {
//            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
//            for (ArtistEntity artist : artistEntityList) {
//                printer.printRecord(
//                        artist.getName(),
//                        artist.getDob(),
//                        artist.getGender() != null ? artist.getGender().name() : null,
//                        artist.getAddress() != null ? artist.getAddress() : null,
//                        artist.getNoOfAlbumsReleased() != null ? artist.getNoOfAlbumsReleased() : null,
//                        artist.getFirstReleaseYear() != null ? artist.getFirstReleaseYear().toString() : null,
//                        artist.getCreatedAt() != null ? artist.getCreatedAt().toString() : null,
//                        artist.getUpdatedAt() != null ? artist.getUpdatedAt() : null);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void exportCSV(HttpServletResponse response) throws IOException {

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + "Artist.csv" + "\"");
        response.setCharacterEncoding("UTF-8");

        String[] header = { "ID", "Name", "DOB", "Gender","Address", "No Of Albums Released", "First Release Year", "Created At", "Updated At" };

        List<ArtistEntity> artistEntityList = this.artistRepository.findAll();
        try(CSVWriter writer = new CSVWriter(response.getWriter(), CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.NO_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END)) {

            writer.writeNext(header);

            // add data to csv
            for (ArtistEntity artist : artistEntityList) {
                writer.writeNext(new String[]{
                        artist.getId().toString(),
                        artist.getName(),
                        String.valueOf(artist.getDob()),
                        artist.getGender() != null ? artist.getGender().name() : null,
                        artist.getAddress() != null ? artist.getAddress() : null,
                        String.valueOf(artist.getNoOfAlbumsReleased() != null ? artist.getNoOfAlbumsReleased() : null),
                        artist.getFirstReleaseYear() != null ? artist.getFirstReleaseYear().toString() : null,
                        artist.getCreatedAt() != null ? artist.getCreatedAt().toString() : null,
                        artist.getUpdatedAt() != null ? String.valueOf(artist.getUpdatedAt()) : null
                });

            }
            writer.flush();
        }
        catch (IOException e) {
            throw new RuntimeException("Error while exporting CSV: " + e.getMessage(), e);
        }
    }
}
