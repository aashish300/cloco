package artist_management_system.java.Service.Impl;

import artist_management_system.java.Model.ArtistEntity;
import artist_management_system.java.Repository.IArtistRepository;
import artist_management_system.java.Service.IArtistService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.csv.CSVParser;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.List;
import java.util.Objects;

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
    public boolean saveAllCSV(MultipartFile file) {
        if (!Objects.equals(file.getContentType(), "text/csv")) {
            return false;
        }
        try (BufferedReader bReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
             CSVParser csvParser = new CSVParser(bReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            this.artistRepository.saveAllCSV(csvParser);
            return true;
        } catch (IOException e) {
            throw new RuntimeException("CSV data is failed to parse: " + e.getMessage());
        }
    }

    @Override
    public void exportCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.addHeader("Content-Disposition", "attachment; filename=\"Artist.csv\"");
        List<ArtistEntity> artistEntityList = this.artistRepository.findAll();
        Writer writer = response.getWriter();
        try {
            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
            for (ArtistEntity artist : artistEntityList) {
                printer.printRecord(
                        artist.getName(),
                        artist.getDob(),
                        artist.getGender() != null ? artist.getGender().name() : null,
                        artist.getAddress(),
                        artist.getNoOfAlbumsReleased(),
                        artist.getFirstReleaseYear(),
                        artist.getCreatedAt(),
                        artist.getUpdatedAt());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
