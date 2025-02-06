package artist_management_system.java.Service;

import artist_management_system.java.Model.ArtistEntity;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IArtistService {

    ArtistEntity save(ArtistEntity artist);

    ArtistEntity findById(Integer id);

    ArtistEntity update(ArtistEntity artist);

    boolean deleteById(Integer id);

    List<ArtistEntity> findAllByPagination(Integer page, Integer size);

    List<ArtistEntity> findAll();

    boolean saveAllCSV(MultipartFile file);

    void exportCSV(HttpServletResponse response) throws IOException;

}
